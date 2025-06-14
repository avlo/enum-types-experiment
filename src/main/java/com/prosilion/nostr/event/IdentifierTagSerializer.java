package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class IdentifierTagSerializer extends JsonSerializer<IdentifierTag> {

  @Override
  public void serialize(IdentifierTag value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
    jsonGenerator.writeStartArray();
    jsonGenerator.writeString("d");
    jsonGenerator.writeString(value.getUuid());
    jsonGenerator.writeEndArray();
  }

}
