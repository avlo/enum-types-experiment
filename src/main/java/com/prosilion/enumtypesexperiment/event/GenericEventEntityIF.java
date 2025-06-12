package com.prosilion.enumtypesexperiment.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.prosilion.enumtypesexperiment.NostrException;
import java.beans.Transient;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

import static com.prosilion.enumtypesexperiment.event.Encoder.ENCODER_MAPPED_AFTERBURNER;

public interface GenericEventEntityIF extends GenericEventDtoIF, IEvent, ISignableEntity {
//  @Override
//  String toBech32() throws NostrException;

  default String serialize() throws NostrException {
    var arrayNode = JsonNodeFactory.instance.arrayNode();

    try {
      arrayNode.add(0);
      arrayNode.add(getPubKey().toString());
      arrayNode.add(getCreatedAt());
      arrayNode.add(getKind());
      arrayNode.add(ENCODER_MAPPED_AFTERBURNER.valueToTree(getTags()));
      arrayNode.add(getContent());

      return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(arrayNode);
    } catch (JsonProcessingException e) {
      throw new NostrException(e);
    }
  }

  @Transient
  @Override
  Supplier<ByteBuffer> getByeArraySupplier();

//  boolean equals(Object o);

//  boolean canEqual(Object other);

//  int hashCode();

//  String getId();
//
//  PublicKey getPubKey();
//
//  Long getCreatedAt();
//
//  Integer getKind();
//
//  List<BaseTag> getTags();
//
//  String getContent();
//
//  Signature getSignature();

//  byte[] get_serializedEvent();
//
//  Integer getNip();
}
