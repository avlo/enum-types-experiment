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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.stream.Streams;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = TagDeserializer.class)
@JsonSerialize(using = BaseTagSerializer.class)
public abstract class BaseTag implements ITag {

    @Override
    public String getCode() {
        return this.getClass().getAnnotation(Tag.class).code();
    }

    public Optional<String> getFieldValue(Field field) {
        try {
            return Optional.ofNullable(
                    new PropertyDescriptor(field.getName(), this.getClass())
                        .getReadMethod().invoke(this))
                .map(Object::toString);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException ex) {
            return Optional.empty();
        }
    }

    public List<Field> getSupportedFields() {
        return Streams.failableStream(Arrays.stream(this.getClass().getDeclaredFields()))
            .filter(f ->
                Objects.nonNull(f.getAnnotation(Key.class)))
            .filter(f ->
                getFieldValue(f).isPresent())
            .collect(Collectors.toList());
    }

    protected static <T extends BaseTag> void setOptionalField(JsonNode node, BiConsumer<JsonNode, T> con, T tag) {
        Optional.ofNullable(node).ifPresent(n -> con.accept(n, tag));
    }

    protected static <T extends BaseTag> void setRequiredField(JsonNode node, BiConsumer<JsonNode, T> con, T tag) {
        con.accept(Optional.ofNullable(node).orElseThrow(), tag);
    }
}
