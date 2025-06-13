package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import com.prosilion.enumtypesexperiment.crypto.NostrUtil;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@EqualsAndHashCode(callSuper = false)
public class GenericEventEntity implements GenericEventEntityIF {
  private static final Log log = LogFactory.getLog(GenericEventEntity.class);
  private final GenericEventDto genericEventDto;

  //  @Key
//  @EqualsAndHashCode.Include
//  private final String id;
  @Override
  public String getId() {
    return genericEventDto.getId();
  }

  //
//  @Key
//  @JsonProperty("pubkey")
//  @EqualsAndHashCode.Include
//  @JsonDeserialize(using = PublicKeyDeserializer.class)
//  private final PublicKey pubKey;
  @Override
  public PublicKey getPubKey() {
    return genericEventDto.getPubKey();
  }

  //
//  @Key
//  @JsonProperty("created_at")
//  @EqualsAndHashCode.Exclude
//  private final Long createdAt;
  @Override
  public Long getCreatedAt() {
    return genericEventDto.getCreatedAt();
  }

  //
//  @Key
//  @EqualsAndHashCode.Exclude
//  private final Integer kind;
  @Override
  public Kind getKind() {
    return genericEventDto.getKind();
  }

  //
//  @Key
//  @EqualsAndHashCode.Exclude
//  @JsonProperty("tags")
//  private final List<BaseTag> tags;
  @Override
  public List<BaseTag> getTags() {
    return genericEventDto.getTags();
  }

  //
//  @Key
//  @EqualsAndHashCode.Exclude
//  private final String content;
  @Override
  public String getContent() {
    return genericEventDto.getContent();
  }

  //
//  @Key
//  @JsonProperty("sig")
//  @EqualsAndHashCode.Exclude
//  @JsonDeserialize(using = SignatureDeserializer.class)
//  private final Signature signature;
  @Override
  public Signature getSignature() {
    return genericEventDto.getSignature();
  }

//  @JsonIgnore
//  @EqualsAndHashCode.Exclude
//  private final byte[] _serializedEvent;

//
//  @JsonIgnore
//  @EqualsAndHashCode.Exclude
//  private Integer nip;

  public GenericEventEntity(@NonNull Identity identity, @NonNull Kind kind) throws NostrException, NoSuchAlgorithmException {
    this(identity, kind, new ArrayList<>(), "");
  }

  public GenericEventEntity(@NonNull Identity identity, @NonNull Kind kind, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    this(identity, kind, new ArrayList<>(), content);
  }

  public GenericEventEntity(@NonNull Identity identity, @NonNull Kind kind, @NonNull List<BaseTag> tags) throws NostrException, NoSuchAlgorithmException {
    this(identity, kind, tags, "");
  }

  public GenericEventEntity(@NonNull Identity identity, @NonNull Kind kind, @NonNull List<BaseTag> tags, @NonNull String content) throws NostrException, NoSuchAlgorithmException {

    final GenericEventDto genericEventDtoUnderConstruction = new GenericEventDto(
        identity.getPublicKey(),
        kind,
        tags,
        Instant.now().getEpochSecond(),
        content);

    Supplier<ByteBuffer> byteArraySupplier = genericEventDtoUnderConstruction.getByteArraySupplier();
    Signature signature = identity.sign(genericEventDtoUnderConstruction);
    String id = NostrUtil.bytesToHex(NostrUtil.sha256(byteArraySupplier.get().array()));

    genericEventDtoUnderConstruction.setId(id);
    genericEventDtoUnderConstruction.setSignature(signature);
    this.genericEventDto = genericEventDtoUnderConstruction;
  }

  @Override
  public String toBech32() {
    return genericEventDto.toBech32();
  }

  @Override
  public Supplier<ByteBuffer> getByteArraySupplier() throws NostrException {
    return genericEventDto.getByteArraySupplier();
  }
}
