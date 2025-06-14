package com.prosilion.nostr.event;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.Serial;

public class GenericTagSerializer extends AbstractTagSerializer<GenericTag> {

  @Serial
  private static final long serialVersionUID = -5318614324350049034L;

  public GenericTagSerializer() {
    super(GenericTag.class);
  }

  @Override
  protected void applyCustomAttributes(ObjectNode node, GenericTag value) {
    value.getAttributes().forEach(a -> node.put(a.getName(), a.getValue().toString()));
  }
}
