package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

import static com.prosilion.nostr.event.IDecoder.I_DECODER_MAPPER_AFTERBURNER;

public class GenericEventDecoder {
  public static GenericEventDto decode(String jsonEvent) throws JsonProcessingException {
    I_DECODER_MAPPER_AFTERBURNER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    return I_DECODER_MAPPER_AFTERBURNER.readValue(jsonEvent, GenericEventDto.class);
  }
}
