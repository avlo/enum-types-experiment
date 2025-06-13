package com.prosilion.enumtypesexperiment.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import java.beans.Transient;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.function.Supplier;

import static com.prosilion.enumtypesexperiment.event.Encoder.ENCODER_MAPPED_AFTERBURNER;

public interface GenericEventDtoIF extends IEvent, ISignableEntity
//    , ISignableDto 
{
  default String serialize() throws NostrException {
    var arrayNode = JsonNodeFactory.instance.arrayNode();

    try {
      arrayNode.add(0);
      arrayNode.add(getPubKey().toString());
      arrayNode.add(getCreatedAt());
      arrayNode.add(getKind().getValue());
      arrayNode.add(ENCODER_MAPPED_AFTERBURNER.valueToTree(getTags()));
      arrayNode.add(getContent());

      return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(arrayNode);
    } catch (JsonProcessingException e) {
      throw new NostrException(e);
    }
  }

//  @Transient
//  Supplier<ByteBuffer> getByteArraySupplier();
  
  String getId();

  PublicKey getPubKey();

  Long getCreatedAt();

  Kind getKind();

  List<BaseTag> getTags();

  String getContent();

  Signature getSignature();

//  byte[] get_serializedEvent();

//  Integer getNip();

  //  void setPubKey(PublicKey pubKey);
//
//  void setCreatedAt(Long createdAt);
//
//  void setKind(Integer kind);
//
//  void setTags(List<BaseTag> tags);
//
//  void setContent(String content);
//
//  void setSignature(Signature signature);
//
//  void set_serializedEvent(byte[] _serializedEvent);
//
//  void setNip(Integer nip);
  String toBech32();

  String toString();

  boolean equals(Object o);

//  boolean canEqual(Object other);

  int hashCode();

//  void setKind(Kind kind);
}
