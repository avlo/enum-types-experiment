package com.prosilion.nostr.event;

import com.prosilion.nostr.crypto.NostrUtil;
import com.prosilion.nostr.crypto.bech32.Bech32Prefix;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class PublicKey extends BaseKey {

  public PublicKey(byte[] rawData) {
    super(KeyType.PUBLIC, rawData, Bech32Prefix.NPUB);
  }

  public PublicKey(String hexPubKey) {
    super(KeyType.PUBLIC, NostrUtil.hexToBytes(hexPubKey), Bech32Prefix.NPUB);
  }
}
