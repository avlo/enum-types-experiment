package com.prosilion.nostr.event;

import java.io.Serial;

public class BaseTagSerializer extends AbstractTagSerializer<BaseTag> {

  @Serial
  private static final long serialVersionUID = -3877972991082754068L;

  public BaseTagSerializer() {
    super(BaseTag.class);
  }
}
