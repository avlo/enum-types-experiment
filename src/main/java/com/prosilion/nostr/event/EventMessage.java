package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.prosilion.nostr.codec.BaseEventEncoder;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import static com.prosilion.nostr.event.Encoder.ENCODER_MAPPED_AFTERBURNER;
import static com.prosilion.nostr.event.IDecoder.I_DECODER_MAPPER_AFTERBURNER;

public record EventMessage(
    @Getter GenericEventDto event,
    @Getter @Nullable String subscriptionId) implements BaseMessage {

  public static Command command = Command.EVENT;
  private static final int SIZE_JSON_EVENT_wo_SIG_ID = 2;
  private static final Function<Object[], Boolean> isEventWoSig = (objArr) ->
      Objects.equals(SIZE_JSON_EVENT_wo_SIG_ID, objArr.length);

  public EventMessage {
//    TODO: fix null check
    subscriptionId = Objects.nonNull(subscriptionId) ? BaseMessage.validateSubscriptionId(subscriptionId) : null;
  }

  @Override
  public String encode() throws JsonProcessingException {
    var arrayNode = JsonNodeFactory.instance.arrayNode().add(getCommand().name());
    Optional.ofNullable(getSubscriptionId())
        .ifPresent(arrayNode::add);
    arrayNode.add(ENCODER_MAPPED_AFTERBURNER.readTree(
        new BaseEventEncoder<>(getEvent()).encode()));
    return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(arrayNode);
  }

  public static <T extends BaseMessage> T decode(@NonNull String jsonString) {
    try {
      Object[] msgArr = I_DECODER_MAPPER_AFTERBURNER.readValue(jsonString, Object[].class);
      return isEventWoSig.apply(msgArr) ? processEvent(jsonString) : processEvent(msgArr, jsonString);
    } catch (Exception e) {
      throw new AssertionError("Invalid argument: " + jsonString);
    }
  }

  private static <T extends BaseMessage> T processEvent(String json) throws JsonProcessingException {
    return (T) new EventMessage(convertValue(json), null);
  }

  private static <T extends BaseMessage> T processEvent(Object[] msgArr, String json) throws JsonProcessingException {
    return (T) new EventMessage(convertValue(json), msgArr[1].toString());
  }

  private static GenericEventDto convertValue(String json) throws JsonProcessingException {
    return I_DECODER_MAPPER_AFTERBURNER.readValue(json, GenericEventDto.class);
  }

  @Override
  public Command getCommand() {
    return command;
  }
}
