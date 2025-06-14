package com.prosilion.nostr.service;

import com.prosilion.nostr.event.TextNoteEventRxR;

public interface EventKindServiceRxRIF<T extends TextNoteEventRxR> {
  void processIncomingEvent(T event);
}
