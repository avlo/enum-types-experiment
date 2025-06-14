package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.lang.NonNull;

public class RelaysTagSerializer extends JsonSerializer<RelaysTag> {

  @Override
  public void serialize(@NonNull RelaysTag relaysTag, @NonNull JsonGenerator jsonGenerator, @NonNull SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeStartArray();
    jsonGenerator.writeString("relays");
    for (Relay json : relaysTag.getRelays()) {
      writeString(jsonGenerator, json.getUri());
    }
    jsonGenerator.writeEndArray();
  }

  private static void writeString(JsonGenerator jsonGenerator, String json) throws IOException {
    jsonGenerator.writeString(json);
  }
}
