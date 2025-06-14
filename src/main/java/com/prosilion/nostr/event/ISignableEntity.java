package com.prosilion.nostr.event;

import com.prosilion.nostr.NostrException;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

public interface ISignableEntity {
  //    Signature getSignature();
  Supplier<ByteBuffer> getByteArraySupplier() throws NostrException;
}
