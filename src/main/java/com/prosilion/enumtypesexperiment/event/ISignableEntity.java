package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.NostrException;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

public interface ISignableEntity {
    Signature getSignature();
    Supplier<ByteBuffer> getByteArraySupplier() throws NostrException;
}
