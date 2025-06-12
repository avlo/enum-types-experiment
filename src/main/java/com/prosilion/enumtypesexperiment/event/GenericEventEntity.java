package com.prosilion.enumtypesexperiment.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import com.prosilion.enumtypesexperiment.crypto.NostrUtil;
import com.prosilion.enumtypesexperiment.crypto.bech32.Bech32;
import com.prosilion.enumtypesexperiment.crypto.bech32.Bech32Prefix;
import java.beans.Transient;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static com.prosilion.enumtypesexperiment.event.Encoder.ENCODER_MAPPED_AFTERBURNER;

@Getter
@EqualsAndHashCode(callSuper = false)
public class GenericEventEntity implements IEvent, ISignableEntity {
    private static final Log log = LogFactory.getLog(GenericEventEntity.class);

    @Key
    @EqualsAndHashCode.Include
    private final String id;

    @Key
    @JsonProperty("pubkey")
    @EqualsAndHashCode.Include
    @JsonDeserialize(using = PublicKeyDeserializer.class)
    private final PublicKey pubKey;

    @Key
    @JsonProperty("created_at")
    @EqualsAndHashCode.Exclude
    private final Long createdAt;

    @Key
    @EqualsAndHashCode.Exclude
    private final Integer kind;

    @Key
    @EqualsAndHashCode.Exclude
    @JsonProperty("tags")
    private final List<BaseTag> tags;

    @Key
    @EqualsAndHashCode.Exclude
    private final String content;

    @Key
    @JsonProperty("sig")
    @EqualsAndHashCode.Exclude
    @JsonDeserialize(using = SignatureDeserializer.class)
    private final Signature signature;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private final byte[] _serializedEvent;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Integer nip;

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
        this.pubKey = identity.getPublicKey();
        this.kind = kind.getValue();
        this.tags = tags;
        this.content = content;
        this._serializedEvent = this.serialize().getBytes(StandardCharsets.UTF_8);
        this.id = NostrUtil.bytesToHex(NostrUtil.sha256(_serializedEvent));
        this.createdAt = Instant.now().getEpochSecond();
        this.signature = identity.sign(this);
    }

    @Override
    public String toBech32() throws NostrException {
        try {
            return Bech32.toBech32(Bech32Prefix.NOTE, this.getId());
        } catch (Exception e) {
            throw new NostrException(e);
        }
    }

    private String serialize() throws NostrException {
        var mapper = ENCODER_MAPPED_AFTERBURNER;
        var arrayNode = JsonNodeFactory.instance.arrayNode();

        try {
            arrayNode.add(0);
            arrayNode.add(this.pubKey.toString());
            arrayNode.add(this.createdAt);
            arrayNode.add(this.kind);
            arrayNode.add(mapper.valueToTree(tags));
            arrayNode.add(this.content);

            return mapper.writeValueAsString(arrayNode);
        } catch (JsonProcessingException e) {
            throw new NostrException(e);
        }
    }

    @Transient
    @Override
    public Supplier<ByteBuffer> getByeArraySupplier() {
        log.info(String.format("Serialized event: %s", new String(this.get_serializedEvent())));
        return () -> ByteBuffer.wrap(this.get_serializedEvent());
    }
}
