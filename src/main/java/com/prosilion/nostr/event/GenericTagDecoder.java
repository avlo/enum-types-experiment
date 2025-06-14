package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.stream.IntStream;
import lombok.NonNull;

public class GenericTagDecoder implements IDecoder<GenericTag> {

  @Override
  public GenericTag decode(@NonNull String json) {
    try {
      String[] jsonElements = I_DECODER_MAPPER_AFTERBURNER.readValue(json, String[].class);
      return new GenericTag(
          jsonElements[0],
          IntStream.of(1, jsonElements.length - 1)
              .mapToObj(i -> new ElementAttribute(
                  "param".concat(String.valueOf(i - 1)),
                  jsonElements[i]))
              .distinct()
              .toList());
    } catch (JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }
  }
}
