package com.prosilion.enumtypesexperiment.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import com.prosilion.enumtypesexperiment.crypto.HexStringValidator;
import com.prosilion.enumtypesexperiment.crypto.bech32.Bech32;
import com.prosilion.enumtypesexperiment.crypto.bech32.Bech32Prefix;
import java.beans.Transient;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;

@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GenericEventDto implements GenericEventDtoIF {
  private static final Log log = LogFactory.getLog(GenericEventDto.class);

  @Key
  @EqualsAndHashCode.Include
  private String id;

  @Key
  @JsonProperty("pubkey")
  @EqualsAndHashCode.Include
  @JsonDeserialize(using = PublicKeyDeserializer.class)
  private PublicKey pubKey;

  @Key
  @JsonProperty("created_at")
  @EqualsAndHashCode.Exclude
  private Long createdAt;

  @Key
  @EqualsAndHashCode.Exclude
  private Integer kind;

  @Key
  @EqualsAndHashCode.Exclude
  @JsonProperty("tags")
  private List<BaseTag> tags;

  @Key
  @EqualsAndHashCode.Exclude
  private String content;

  @Key
  @JsonProperty("sig")
  @EqualsAndHashCode.Exclude
  @JsonDeserialize(using = SignatureDeserializer.class)
  private Signature signature;

//  public GenericEventDto(@NonNull String id) {
//    this.tags = new ArrayList<>();
//    setId(id);
//  }

  public GenericEventDto(@NonNull String id, @NonNull PublicKey pubKey, @NonNull Kind kind, @NonNull Long createdAt, @NonNull Signature signature) {
    this(id, pubKey, kind, new ArrayList<>(), createdAt, "", signature);
  }

  public GenericEventDto(@NonNull String id, @NonNull PublicKey pubKey, @NonNull Kind kind, @NonNull Long createdAt, @NonNull List<BaseTag> tags, @NonNull Signature signature) throws NostrException, NoSuchAlgorithmException {
    this(id, pubKey, kind, tags, createdAt, "", signature);
  }

  public GenericEventDto(@NonNull String id, @NonNull PublicKey pubKey, @NonNull Kind kind, @NonNull List<BaseTag> tags, @NonNull Long createdAt, @NonNull String content, @NonNull Signature signature) {
    this.id = validateId(id);
    this.pubKey = pubKey;
    this.kind = kind.getValue();
    this.tags = tags;
    this.createdAt = createdAt;
    this.content = content;
    this.signature = signature;
  }

  /**
   * should only be used by GenericEventEntity
   */
  protected GenericEventDto(@NonNull PublicKey pubKey, @NonNull Kind kind, @NonNull List<BaseTag> tags, @NonNull Long createdAt, @NonNull String content) {
    this.pubKey = pubKey;
    this.kind = kind.getValue();
    this.tags = tags;
    this.createdAt = createdAt;
    this.content = content;
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

  public void setId(@NonNull String id) {
    this.id = validateId(id);
  }

  @Override
  public Kind getKind() {
    return Kind.valueOf(kind);
  }

  public void setKind(Kind kind) {
    this.kind = kind.getValue();
  }

  public void setSignature(@NonNull Signature signature) {
    this.signature = signature;
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
