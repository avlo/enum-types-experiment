package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.crypto.HexStringValidator;
import java.util.List;

public record GenericEventRecord(String id, PublicKey pubkey, Long createdAt, Kind kind, List<BaseTag> tags,
                                 String content, Signature signature) {
  public GenericEventRecord {
    id = HexStringValidator.validateHex(id, 64);
  }
}
