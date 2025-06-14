package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;
import lombok.NonNull;

public class BaseMessageDecoder<T extends BaseMessage> implements IDecoder<T> {
  public static final int COMMAND_INDEX = 0;
  public static final int ARG_INDEX = 1;

  @Override
  public T decode(@NonNull String jsonString) throws JsonProcessingException {
    ValidNostrJsonStructure validNostrJsonStructure = validateProperlyFormedJson(jsonString);
    Object subscriptionId = validNostrJsonStructure.getSubscriptionId();

    return switch (Command.valueOf(validNostrJsonStructure.getCommand())) {
//          client <-> relay messages
      case Command.AUTH -> subscriptionId instanceof Map map ?
          CanonicalAuthenticationMessage.decode(map) :
          RelayAuthenticationMessage.decode(subscriptionId);
      case Command.EVENT -> EventMessage.decode(jsonString);
//            missing client <-> relay handlers
//            case "COUNT" -> CountMessage.decode(subscriptionId);

//            client -> relay messages
      case Command.CLOSE -> CloseMessage.decode(subscriptionId);
      case Command.REQ -> ReqMessage.decode(subscriptionId, jsonString);

//            relay -> client handlers
      case Command.EOSE -> EoseMessage.decode(subscriptionId);
      case Command.NOTICE -> NoticeMessage.decode(subscriptionId);
      case Command.OK -> OkMessage.decode(jsonString);
//            missing relay -> client handlers
//            case "CLOSED" -> Closed.message.decode(subscriptionId);
      default ->
          throw new IllegalArgumentException(String.format("Invalid JSON command [%s] in JSON string [%s] ", validNostrJsonStructure.getCommand(), jsonString));
    };
  }

  private ValidNostrJsonStructure validateProperlyFormedJson(@NonNull String jsonString) throws JsonProcessingException {
    return new ValidNostrJsonStructure(
        I_DECODER_MAPPER_AFTERBURNER.readTree(jsonString).get(COMMAND_INDEX).asText(),
        I_DECODER_MAPPER_AFTERBURNER.readTree(jsonString).get(ARG_INDEX).asText());
  }

  private record ValidNostrJsonStructure(
      @NonNull String getCommand,
      @NonNull Object getSubscriptionId) {
  }
}
