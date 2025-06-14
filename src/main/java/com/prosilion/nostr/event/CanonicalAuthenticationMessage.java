package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.prosilion.nostr.Kind;
import com.prosilion.nostr.codec.BaseEventEncoder;
import com.prosilion.nostr.filter.Filterable;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;

import static com.prosilion.nostr.event.Encoder.ENCODER_MAPPED_AFTERBURNER;
import static com.prosilion.nostr.event.IDecoder.I_DECODER_MAPPER_AFTERBURNER;

@Getter
public class CanonicalAuthenticationMessage extends BaseAuthMessage {

  public static final String CHALLENGE = "challenge";
  public static final String RELAY = "relay";
  @JsonProperty
  private final GenericEventDto eventDto;

  public CanonicalAuthenticationMessage(GenericEventDto eventDto) {
    super(Command.AUTH);
    this.eventDto = eventDto;
  }

  @Override
  public String encode() throws JsonProcessingException {
    return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(
        JsonNodeFactory.instance.arrayNode()
            .add(getCommand().name())
            .add(ENCODER_MAPPED_AFTERBURNER.readTree(
                new BaseEventEncoder<>(getEventDto()).encode())));
  }

  @SneakyThrows
  public static <T extends BaseMessage> T decode(@NonNull Map map) {
    GenericEventDto event = I_DECODER_MAPPER_AFTERBURNER.convertValue(map, new TypeReference<>() {
    });

    List<GenericTag> genericTags = event.getTags().stream()
        .filter(GenericTag.class::isInstance)
        .map(GenericTag.class::cast).toList();

    BaseTag challengeTag = GenericTag.create(CHALLENGE, getAttributeValue(genericTags, CHALLENGE));
    List<RelaysTag> relayTags = Filterable.getTypeSpecificTags(RelaysTag.class, event);

    List<BaseTag> list = Stream.concat(Stream.of(challengeTag), relayTags.stream()).toList();

    GenericEventDto canonEvent = new GenericEventDto(
        map.get("id").toString(),
        event.getPublicKey(),
        Kind.CLIENT_AUTH,
        event.getCreatedAt(),
        list,
        event.getSignature());

    return (T) new CanonicalAuthenticationMessage(canonEvent);
  }

  private static String getAttributeValue(List<GenericTag> genericTags, String attributeName) {
//    TODO: stream optional
    return genericTags.stream()
        .filter(tag -> tag.getCode().equalsIgnoreCase(attributeName)).map(GenericTag::getAttributes).toList().get(0).get(0).getValue().toString();
  }
}
