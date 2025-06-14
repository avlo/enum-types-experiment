package com.prosilion.nostr.service.plugin;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.GenericEventEntity;

public interface EventKindPluginIF<T extends GenericEventEntity> {
  void processIncomingEvent(T event);

  Kind getKind();
}
