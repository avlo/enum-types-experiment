package com.prosilion.enumtypesexperiment.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import com.prosilion.enumtypesexperiment.crypto.NostrUtil;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;
import lombok.NonNull;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static com.prosilion.enumtypesexperiment.event.Encoder.ENCODER_MAPPED_AFTERBURNER;

public class GenericEventEntityFactory {
  private static final Log log = LogFactory.getLog(GenericEventEntityFactory.class);

  public static GenericEventRecord createInstance(@NonNull Identity identity, @NonNull Kind kind, @NonNull List<BaseTag> tags, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    long epochSecond = Instant.now().getEpochSecond();
    GenericEventRecordFlux flux = new GenericEventRecordFlux(
        identity.getPublicKey(),
        epochSecond,
        kind, tags, content);

    Supplier<ByteBuffer> byteArraySupplier = flux.getByteArraySupplier();
    Signature signature = identity.sign(flux);

    return new GenericEventRecord(
        NostrUtil.bytesToHex(NostrUtil.sha256(byteArraySupplier.get().array())),
        identity.getPublicKey(),
        epochSecond,
        kind,
        tags,
        content,
        signature);
  }

  private record GenericEventRecordFlux(PublicKey pubkey, Long createdAt, Kind kind, List<BaseTag> tags,
                                        String content) implements ISignableEntity {

    public Supplier<ByteBuffer> getByteArraySupplier() throws NostrException {
      byte[] serializedEvent = serialize().getBytes(StandardCharsets.UTF_8);
      log.info(String.format("Serialized event: %s", new String(serializedEvent)));
      return () -> ByteBuffer.wrap(serializedEvent);
    }

    public String serialize() throws NostrException {
      var arrayNode = JsonNodeFactory.instance.arrayNode();

      try {
        arrayNode.add(0);
        arrayNode.add(pubkey.toString());
        arrayNode.add(createdAt);
        arrayNode.add(kind.getValue());
        arrayNode.add(ENCODER_MAPPED_AFTERBURNER.valueToTree(tags));
        arrayNode.add(content);

        return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(arrayNode);
      } catch (JsonProcessingException e) {
        throw new NostrException(e);
      }
    }
  }
}
