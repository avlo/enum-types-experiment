package com.prosilion.nostr.event;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.NonNull;

@JsonSerialize(using = GenericTagSerializer.class)
public record GenericTag(
    @Getter String code,
    @Getter List<ElementAttribute> attributes) implements BaseTag {

  public GenericTag(String code, ElementAttribute... attribute) {
    this(code, List.of(attribute));
  }

  public GenericTag(String code, List<ElementAttribute> attributes) {
    this.code = code;
    this.attributes = attributes;
  }

  public static GenericTag create(@NonNull String code, @NonNull String... params) {
    return create(code, List.of(params));
  }

  public static GenericTag create(@NonNull String code, @NonNull List<String> params) {
    return new GenericTag(code,
        IntStream.range(0, params.size())
            .mapToObj(i ->
                new ElementAttribute("param".concat(String.valueOf(i)), params.get(i)))
            .toList());
  }
}


