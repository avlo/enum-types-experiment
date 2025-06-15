package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import java.util.stream.IntStream;
import lombok.NonNull;

//public interface IDecoder<T extends IElement> {
public interface IDecoder<T extends BaseTag> {
  ObjectMapper I_DECODER_MAPPER_AFTERBURNER = JsonMapper
      .builder().addModule(
          new AfterburnerModule())
      .build().configure(
          JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

  static <T extends GenericTag> T decode(@NonNull String json) {
    try {
      String[] jsonElements = I_DECODER_MAPPER_AFTERBURNER.readValue(json, String[].class);
      return (T) new GenericTag(
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
