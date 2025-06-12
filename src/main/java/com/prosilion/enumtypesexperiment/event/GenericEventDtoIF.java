package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import java.util.List;

public interface GenericEventDtoIF extends IEvent
//    , ISignableDto 
{
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
