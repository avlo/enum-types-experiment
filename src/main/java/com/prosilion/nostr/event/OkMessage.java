package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.Getter;
import org.springframework.lang.NonNull;

import static com.prosilion.nostr.event.Encoder.ENCODER_MAPPED_AFTERBURNER;
import static com.prosilion.nostr.event.IDecoder.I_DECODER_MAPPER_AFTERBURNER;

@Getter
public class OkMessage extends BaseMessage {

  @JsonProperty
  private final String eventId;

  @JsonProperty
  private final Boolean flag;

  @JsonProperty
  private final String message;

  public OkMessage(String eventId, Boolean flag, String message) {
    super(Command.OK);
    this.eventId = eventId;
    this.flag = flag;
    this.message = message;
  }

  @Override
  public String encode() throws JsonProcessingException {
    return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(
        JsonNodeFactory.instance.arrayNode()
            .add(getCommand().name())
            .add(getEventId())
            .add(getFlag())
            .add(getMessage()));
  }

  public static <T extends BaseMessage> T decode(@NonNull String jsonString) {
    try {
      Object[] msgArr = I_DECODER_MAPPER_AFTERBURNER.readValue(jsonString, Object[].class);
      return (T) new OkMessage(msgArr[1].toString(), (Boolean) msgArr[2], msgArr[3].toString());
    } catch (JsonProcessingException e) {
      throw new AssertionError(e);
    }
  }
}
