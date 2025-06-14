package com.prosilion.nostr.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import org.apache.commons.lang3.stream.Streams;

//TODO: revisit below serializers
@JsonDeserialize(using = TagDeserializer.class)
@JsonSerialize(using = BaseTagSerializer.class)
public interface BaseTag extends ITag {

  default String getCode() {
    return this.getClass().getAnnotation(Tag.class).code();
  }

  default Optional<String> getFieldValue(Field field) {
    try {
      return Optional.ofNullable(
              new PropertyDescriptor(field.getName(), this.getClass())
                  .getReadMethod().invoke(this))
          .map(Object::toString);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |
             IntrospectionException ex) {
      return Optional.empty();
    }
  }

  default List<Field> getSupportedFields() {
    return Streams.failableStream(Arrays.stream(this.getClass().getDeclaredFields()))
        .filter(f ->
            Objects.nonNull(f.getAnnotation(Key.class)))
        .filter(f ->
            getFieldValue(f).isPresent())
        .collect(Collectors.toList());
  }

  static <T extends BaseTag> void setOptionalField(JsonNode node, BiConsumer<JsonNode, T> con, T tag) {
    Optional.ofNullable(node).ifPresent(n -> con.accept(n, tag));
  }

  static <T extends BaseTag> void setRequiredField(JsonNode node, BiConsumer<JsonNode, T> con, T tag) {
    con.accept(Optional.ofNullable(node).orElseThrow(), tag);
  }
}
