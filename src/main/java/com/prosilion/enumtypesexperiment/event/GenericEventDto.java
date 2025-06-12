package com.prosilion.enumtypesexperiment.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import com.prosilion.enumtypesexperiment.crypto.HexStringValidator;
import com.prosilion.enumtypesexperiment.crypto.bech32.Bech32;
import com.prosilion.enumtypesexperiment.crypto.bech32.Bech32Prefix;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

//  @JsonIgnore
//  @EqualsAndHashCode.Exclude
//  private final byte[] _serializedEvent;

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
  protected GenericEventDto(@NonNull String id, @NonNull PublicKey pubKey, @NonNull Kind kind, @NonNull List<BaseTag> tags, @NonNull Long createdAt, @NonNull String content) {
    this.id = validateId(id);
    this.pubKey = pubKey;
    this.kind = kind.getValue();
    this.tags = tags;
    this.createdAt = createdAt;
    this.content = content;
  }

  private String validateId(@NonNull String id) {
    assert Boolean.parseBoolean(HexStringValidator.validateHex(id, 64)) :
        new AssertionError(String.format("Invalid id [%s]. Length must be exactly 64 but was [%s]", id, id.length()));
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

  @Override
  public Kind getKind() {
    return Kind.valueOf(kind);
  }

  public void setKind(Kind kind) {
    this.kind = kind.getValue();
  }

  public void setSignature(@org.springframework.lang.NonNull Signature signature) {
    this.signature = signature;
  }

  //  @Override
//  public void addTag(BaseTag tag) {
//    tags.add(tag);
//  }
//
//  @Transient
//  @Override
//  public Supplier<ByteBuffer> getByeArraySupplier() {
//    log.info(String.format("Serialized event: %s", new String(this.get_serializedEvent())));
//    return () -> ByteBuffer.wrap(this.get_serializedEvent());
//  }
}
