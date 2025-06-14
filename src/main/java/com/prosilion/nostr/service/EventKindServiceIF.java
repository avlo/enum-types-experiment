package com.prosilion.nostr.service;

import com.prosilion.nostr.event.GenericEventEntityIF;

public interface EventKindServiceIF<T extends GenericEventEntityIF> {
  void processIncomingEvent(T event);
}
