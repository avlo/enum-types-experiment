package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonProcessingException;

import static com.prosilion.nostr.event.IEvent.MAPPER_AFTERBURNER;

public interface BaseTagDecoder {
  static BaseTag decode(String jsonString) throws JsonProcessingException {
      return MAPPER_AFTERBURNER.readValue(jsonString, BaseTag.class);
  }
}
