package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.prosilion.nostr.Kind;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Data
@Tag(code = "a", nip = 33)
@JsonPropertyOrder({"kind", "publicKey", "identifierTag", "relay"})
@NoArgsConstructor
@JsonSerialize(using = AddressTagSerializer.class)
public class AddressTag extends BaseTag {

  @Key
  @JsonProperty
  private Kind kind;

  @Key
  @JsonProperty
  private PublicKey publicKey;

  @Key
  @JsonProperty
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private IdentifierTag identifierTag;

  @Key
  @JsonProperty
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Relay relay;

  public AddressTag(@NonNull Kind kind, @NonNull PublicKey publicKey) {
    this.kind = kind;
    this.publicKey = publicKey;
  }

  public AddressTag(@NonNull Kind kind, @NonNull PublicKey publicKey, @NonNull IdentifierTag identifierTag) {
    this(kind, publicKey);
    this.identifierTag = identifierTag;
  }

  public AddressTag(@NonNull Kind kind, @NonNull PublicKey publicKey, @NonNull IdentifierTag identifierTag, @NonNull Relay relay) {
    this(kind, publicKey, identifierTag);
    this.relay = relay;
  }

  public static <T extends BaseTag> T deserialize(@NonNull JsonNode node) {
    List<String> list = Arrays.stream(node.get(1).asText().split(":")).toList();

    final AddressTag addressTag = new AddressTag();

    addressTag.setKind(Kind.valueOf(Optional.ofNullable(list.get(0)).orElseThrow()));
    addressTag.setPublicKey(new PublicKey(Optional.ofNullable(list.get(1)).orElseThrow()));

    Optional.ofNullable(list.get(2)).ifPresent(identifier -> addressTag.setIdentifierTag(new IdentifierTag(identifier)));
    Optional.ofNullable(node.get(2)).ifPresent(relay -> addressTag.setRelay(new Relay(relay.asText())));

    return (T) addressTag;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass())
      return false;
    AddressTag that = (AddressTag) o;
    return
        Objects.equals(kind, that.kind) &&
            Objects.equals(publicKey, that.publicKey) &&
            Objects.equals(identifierTag, that.identifierTag) &&
            Objects.equals(relay, that.relay);
  }

  @Override
  public int hashCode() {
    return Objects.hash(kind, publicKey, identifierTag, relay);
  }
}
