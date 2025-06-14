package com.prosilion.nostr.service;

import com.prosilion.nostr.event.AbstractBadgeAwardEvent;
import com.prosilion.nostr.event.AbstractBadgeAwardEventIF;
import com.prosilion.nostr.event.Type;

public interface EventKindTypeServiceIF<T extends Type, U extends AbstractBadgeAwardEventIF<T>> {
  void processIncomingEvent(U event);
}
