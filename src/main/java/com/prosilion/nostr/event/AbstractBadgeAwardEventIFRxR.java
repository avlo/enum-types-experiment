package com.prosilion.nostr.event;

import com.prosilion.nostr.Kind;

public interface AbstractBadgeAwardEventIFRxR<T extends Type> {
  void doSomething();
  Kind getKind();
  T getType();
}
