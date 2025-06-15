package com.prosilion.nostr.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.prosilion.nostr.Kind;
import com.prosilion.nostr.NostrException;
import com.prosilion.nostr.crypto.HexStringValidator;
import com.prosilion.nostr.crypto.bech32.Bech32;
import com.prosilion.nostr.crypto.bech32.Bech32Prefix;
import java.beans.Transient;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@JsonTypeName("EVENT")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_ARRAY, use = JsonTypeInfo.Id.NAME)
public record GenericEventDto(
    @Getter @EqualsAndHashCode.Include String id,
    @Getter @Nullable @JsonProperty("pubkey") @EqualsAndHashCode.Include PublicKey publicKey,
    @Getter @Nullable @EqualsAndHashCode.Exclude Kind kind,
    @Getter @Nullable @EqualsAndHashCode.Exclude List<BaseTag> tags,
    @Getter @Nullable @JsonProperty("created_at") @EqualsAndHashCode.Exclude Long createdAt,
    @Getter @Nullable @EqualsAndHashCode.Exclude String content,
    @Getter @Nullable @JsonProperty("sig") @EqualsAndHashCode.Exclude Signature signature) implements GenericEventDtoIF {
  private static final Log log = LogFactory.getLog(GenericEventDto.class);

  public GenericEventDto(String id) {
    this(id, null, null, new ArrayList<>(), null, null, null);
  }

  public GenericEventDto(String id, PublicKey publicKey, Kind kind, Long createdAt, Signature signature) {
    this(id, publicKey, kind, new ArrayList<>(), createdAt, "", signature);
  }

  public GenericEventDto(String id, PublicKey publicKey, Kind kind, List<BaseTag> tags, Long createdAt, Signature signature) throws NostrException, NoSuchAlgorithmException {
    this(id, publicKey, kind, tags, createdAt, "", signature);
  }

  public GenericEventDto(String id, PublicKey publicKey, Kind kind, List<BaseTag> tags, Long createdAt, String content, Signature signature) {
    this.id = validateId(id);
    this.publicKey = publicKey;
    this.kind = kind;
    this.tags = tags;
    this.createdAt = createdAt;
    this.content = content;
    this.signature = signature;
  }

  private String validateId(@NonNull String id) {
    HexStringValidator.validateHex(id, 64);
    return id;
  }

  @Override
  public String toBech32() {
    try {
      return Bech32.toBech32(Bech32Prefix.NOTE, this.getId());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

//  @JsonIgnore
//  @EqualsAndHashCode.Exclude
//  private byte[] _serializedEvent;

  @Transient
  @Override
  public Supplier<ByteBuffer> getByteArraySupplier() throws NostrException {
    byte[] serializedEvent = serialize().getBytes(StandardCharsets.UTF_8);
    log.info(String.format("Serialized event: %s", new String(serializedEvent)));
    return () -> ByteBuffer.wrap(serializedEvent);
  }
}
