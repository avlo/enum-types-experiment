package com.prosilion.nostr.service.plugin;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.TextNoteEventRxR;

public interface EventKindPluginRxRIF<T extends TextNoteEventRxR> {
  void processIncomingEvent(T event);

  Kind getKind();
}
