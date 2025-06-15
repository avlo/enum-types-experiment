package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.Getter;
import org.springframework.lang.NonNull;

import static com.prosilion.nostr.event.Encoder.ENCODER_MAPPED_AFTERBURNER;

public record RelayAuthenticationMessage(
    @Getter @JsonProperty String challenge) implements BaseMessage {
  public static Command command = Command.AUTH;

  public RelayAuthenticationMessage(String challenge) {
    this.challenge = challenge;
  }

  @Override
  public String encode() throws JsonProcessingException {
    return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(
        JsonNodeFactory.instance.arrayNode()
            .add(getCommand().name())
            .add(getChallenge()));
  }

  public static <T extends BaseMessage> T decode(@NonNull Object arg) {
    return (T) new RelayAuthenticationMessage(arg.toString());
  }

  @Override
  public Command getCommand() {
    return command;
  }
}
