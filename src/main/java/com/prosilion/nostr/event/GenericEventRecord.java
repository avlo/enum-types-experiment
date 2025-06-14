package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.prosilion.nostr.Kind;
import com.prosilion.nostr.crypto.HexStringValidator;
import java.util.List;
import lombok.Getter;

@JsonTypeName("EVENT")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_ARRAY, use = JsonTypeInfo.Id.NAME)
public record GenericEventRecord(
    @Getter
    String id,

    @Getter
    @JsonProperty("pubkey")
    PublicKey publicKey,

    @Getter
    @JsonProperty("created_at")
    Long createdAt,

    @Getter
    Kind kind,

    @Getter
    @JsonProperty("tags")
    List<BaseTag> tags,

    @Getter
    String content,

    @Getter
    @JsonProperty("sig")
    Signature signature) {

  public GenericEventRecord {
    id = HexStringValidator.validateHex(id, 64);
  }
}
