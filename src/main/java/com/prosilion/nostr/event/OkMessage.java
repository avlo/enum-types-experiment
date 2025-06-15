package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.Getter;
import org.springframework.lang.NonNull;

import static com.prosilion.nostr.event.Encoder.ENCODER_MAPPED_AFTERBURNER;
import static com.prosilion.nostr.event.IDecoder.I_DECODER_MAPPER_AFTERBURNER;

public record OkMessage(
    @Getter @JsonProperty String eventId,
    @Getter @JsonProperty Boolean flag,
    @Getter @JsonProperty String message) implements BaseMessage {
  public static Command command = Command.OK;

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

  @Override
  public Command getCommand() {
    return command;
  }
}
