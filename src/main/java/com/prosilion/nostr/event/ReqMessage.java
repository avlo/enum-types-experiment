package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.prosilion.nostr.filter.Filters;
import java.time.temporal.ValueRange;
import java.util.List;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.lang.NonNull;

import static com.prosilion.nostr.event.Encoder.ENCODER_MAPPED_AFTERBURNER;
import static com.prosilion.nostr.event.IDecoder.I_DECODER_MAPPER_AFTERBURNER;

@Getter
public class ReqMessage extends BaseMessage {
  public static final int FILTERS_START_INDEX = 2;

  @JsonProperty
  private final String subscriptionId;

  @JsonProperty
  private final List<Filters> filtersList;

  public ReqMessage(@NonNull String subscriptionId, @NonNull Filters... filtersList) {
    this(subscriptionId, List.of(filtersList));
  }

  public ReqMessage(@NonNull String subscriptionId, @NonNull List<Filters> filtersList) {
    super(Command.REQ);
    validateSubscriptionId(subscriptionId);
    this.subscriptionId = subscriptionId;
    this.filtersList = filtersList;
  }

  @Override
  public String encode() throws JsonProcessingException {
    var encoderArrayNode = JsonNodeFactory.instance.arrayNode();
    encoderArrayNode
        .add(getCommand().name())
        .add(getSubscriptionId());

    filtersList.stream()
        .map(FiltersEncoder::new)
        .map(FiltersEncoder::encode)
        .map(ReqMessage::createJsonNode)
        .forEach(encoderArrayNode::add);

    return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(encoderArrayNode);
  }

  public static <T extends BaseMessage> T decode(@NonNull Object subscriptionId, @NonNull String jsonString) throws JsonProcessingException {
    validateSubscriptionId(subscriptionId.toString());
    List<String> jsonFiltersList = getJsonFiltersList(jsonString);
    return (T) new ReqMessage(
        subscriptionId.toString(),
        Streams.failableStream(jsonFiltersList.stream()).map(filtersList ->
            FiltersDecoder.decode(filtersList)).stream().toList());
  }

  private static JsonNode createJsonNode(String jsonNode) {
    try {
      return ENCODER_MAPPED_AFTERBURNER.readTree(jsonNode);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(String.format("Malformed encoding ReqMessage json: [%s]", jsonNode), e);
    }
  }

  private static void validateSubscriptionId(String subscriptionId) {
    if (!ValueRange.of(1, 64).isValidIntValue(subscriptionId.length())) {
      throw new IllegalArgumentException(String.format("SubscriptionId length must be between 1 and 64 characters but was [%d]", subscriptionId.length()));
    }
  }

  private static List<String> getJsonFiltersList(String jsonString) throws JsonProcessingException {
    return IntStream.range(FILTERS_START_INDEX, I_DECODER_MAPPER_AFTERBURNER.readTree(jsonString).size())
        .mapToObj(idx -> readTree(jsonString, idx)).toList();
  }

  @SneakyThrows
  private static String readTree(String jsonString, int idx) {
    return I_DECODER_MAPPER_AFTERBURNER.readTree(jsonString).get(idx).toString();
  }
}
