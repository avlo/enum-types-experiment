package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.Optional;

public class TagDeserializer extends JsonDeserializer<BaseTag> {
  @Override
  public BaseTag deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    return switch (Optional.ofNullable(node.get(0)).orElseThrow().asText()) {
      case "a" -> AddressTag.deserialize(node);
      case "d" -> IdentifierTag.deserialize(node);
      case "e" -> EventTag.deserialize(node);
//                case "g" -> GeohashTag.deserialize(node);
      case "p" -> PubKeyTag.deserialize(node);
//                case "t" -> HashtagTag.deserialize(node);
//                case "v" -> VoteTag.deserialize(node);

//                case "nonce" -> NonceTag.deserialize(node);
//                case "price" -> PriceTag.deserialize(node);
//                case "relays" -> RelaysTag.deserialize(node);
//                case "subject" -> SubjectTag.deserialize(node);
      default -> IDecoder.decode(node.toString());
    };
  }
}
