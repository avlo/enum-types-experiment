package com.prosilion.nostr.service;

import com.prosilion.nostr.event.AbstractBadgeAwardEvent;
import com.prosilion.nostr.event.Type;

public interface EventKindTypeServiceIF<T extends Type, U extends AbstractBadgeAwardEvent<T>> {
  void processIncomingEvent(U event);
}
