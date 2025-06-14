package com.prosilion.nostr.service;

import com.prosilion.nostr.event.TextNoteEvent;

public interface EventKindServiceRxRIF<T extends TextNoteEvent> {
  void processIncomingEvent(T event);
}
