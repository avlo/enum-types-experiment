package com.prosilion.enumtypesexperiment.event;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

public interface ISignableEntity {
    Signature getSignature();
    Supplier<ByteBuffer> getByeArraySupplier();
}
