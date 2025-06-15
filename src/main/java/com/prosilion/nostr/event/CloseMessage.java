package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.Getter;
import org.springframework.lang.NonNull;

import static com.prosilion.nostr.event.Encoder.ENCODER_MAPPED_AFTERBURNER;

public record CloseMessage(
    @Getter @JsonProperty String subscriptionId) implements BaseMessage {
  public static Command command = Command.CLOSE;

  public CloseMessage(String subscriptionId) {
    this.subscriptionId = BaseMessage.validateSubscriptionId(subscriptionId);
  }

  @Override
  public String encode() throws JsonProcessingException {
    return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(
        JsonNodeFactory.instance.arrayNode()
            .add(getCommand().name())
            .add(getSubscriptionId()));
  }

  public static <T extends BaseMessage> T decode(@NonNull Object arg) {
    return (T) new CloseMessage(arg.toString());
  }

  @Override
  public Command getCommand() {
    return command;
  }
}
