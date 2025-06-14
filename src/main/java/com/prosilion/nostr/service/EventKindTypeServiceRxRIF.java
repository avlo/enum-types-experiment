package com.prosilion.nostr.service;

import com.prosilion.nostr.event.AbstractBadgeAwardEventIFRxR;
import com.prosilion.nostr.event.Type;

public interface EventKindTypeServiceRxRIF<T extends Type, U extends AbstractBadgeAwardEventIFRxR<T>> {
  void processIncomingEvent(U event);
}
