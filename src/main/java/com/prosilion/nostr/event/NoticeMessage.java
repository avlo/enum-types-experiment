package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.Getter;
import org.springframework.lang.NonNull;

import static com.prosilion.nostr.event.Encoder.ENCODER_MAPPED_AFTERBURNER;

@Getter
public class NoticeMessage extends BaseMessage {

  @JsonProperty
  private final String message;

  public NoticeMessage(@NonNull String message) {
    super(Command.NOTICE);
    this.message = message;
  }

  @Override
  public String encode() throws JsonProcessingException {
    return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(
        JsonNodeFactory.instance.arrayNode()
            .add(getCommand().name())
            .add(getMessage()));
  }

  public static <T extends BaseMessage> T decode(@NonNull Object arg) {
    return (T) new NoticeMessage(arg.toString());
  }
}
