package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.crypto.NostrUtil;
import com.prosilion.enumtypesexperiment.crypto.bech32.Bech32Prefix;
import com.prosilion.enumtypesexperiment.crypto.schnorr.Schnorr;

public class PrivateKey extends BaseKey {

    public PrivateKey(byte[] rawData) {
        super(KeyType.PRIVATE, rawData, Bech32Prefix.NSEC);
    }

    public PrivateKey(String hexPrivKey) {
        super(KeyType.PRIVATE, NostrUtil.hexToBytes(hexPrivKey), Bech32Prefix.NSEC);
    }

    public static PrivateKey generateRandomPrivKey() {
        return new PrivateKey(Schnorr.generatePrivateKey());
    }
}
