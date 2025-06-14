package com.prosilion.nostr.service.plugin;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.TextNoteEvent;

public interface EventKindPluginRxRIF<T extends TextNoteEvent> {
  void processIncomingEvent(T event);

  Kind getKind();
}
