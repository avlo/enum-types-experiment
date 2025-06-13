package com.prosilion.enumtypesexperiment.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.crypto.HexStringValidator;
import java.util.List;

@JsonTypeName("EVENT")
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_ARRAY, use= JsonTypeInfo.Id.NAME)
public record GenericEventRecord(
    String id,
    
    @JsonProperty("pubkey")
    PublicKey publicKey,
    
    @JsonProperty("created_at")
    Long createdAt,
    
    Kind kind,
    
    @JsonProperty("tags")
    List<BaseTag> baseTags,
    
    String content,
    
    @JsonProperty("sig")
    Signature signature) {
  
  public GenericEventRecord {
    id = HexStringValidator.validateHex(id, 64);
  }
}
