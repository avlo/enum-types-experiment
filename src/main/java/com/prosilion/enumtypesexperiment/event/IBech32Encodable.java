package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.NostrException;

public interface IBech32Encodable {
    String toBech32() throws NostrException;
}
