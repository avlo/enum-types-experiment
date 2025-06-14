package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.net.URI;
import java.util.Optional;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Tag(code = "r", nip = 12)
@JsonSerialize(using = ReferenceTagSerializer.class)
public record ReferenceTag(
    @Getter @Key @JsonProperty("uri") URI uri) implements BaseTag {
  public static BaseTag deserialize(@NonNull JsonNode node) {
    return new ReferenceTag(
        URI.create(Optional.of(node.get(1)).orElseThrow().asText())
    );
  }
}
