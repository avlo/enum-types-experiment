package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

//public interface IDecoder<T extends IElement> {
public interface IDecoder<T> {
  ObjectMapper I_DECODER_MAPPER_AFTERBURNER = JsonMapper
      .builder().addModule(
          new AfterburnerModule())
      .build().configure(
          JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

  T decode(String str) throws JsonProcessingException;
}
