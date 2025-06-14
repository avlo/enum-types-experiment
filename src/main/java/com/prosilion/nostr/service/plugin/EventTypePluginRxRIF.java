package com.prosilion.nostr.service.plugin;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.AbstractBadgeAwardEventIFRxR;
import com.prosilion.nostr.event.Type;

public interface EventTypePluginRxRIF<S extends Type, T extends AbstractBadgeAwardEventIFRxR<S>, U extends Kind> {
  void processIncomingEvent(T event);

  U getKind();
}
