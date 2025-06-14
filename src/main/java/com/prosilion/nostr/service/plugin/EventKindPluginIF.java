package com.prosilion.nostr.service.plugin;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.BaseEvent;
import com.prosilion.nostr.event.TextNoteEvent;

public interface EventKindPluginIF<T extends BaseEvent> {
  void processIncomingEvent(T event);

  Kind getKind();
}
