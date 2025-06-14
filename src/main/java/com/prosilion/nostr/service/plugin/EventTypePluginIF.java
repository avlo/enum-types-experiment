package com.prosilion.nostr.service.plugin;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.AbstractBadgeAwardEvent;
import com.prosilion.nostr.event.AbstractBadgeAwardEventIF;
import com.prosilion.nostr.event.Type;

public interface EventTypePluginIF<S extends Type, T extends AbstractBadgeAwardEventIF<S>, U extends Kind> {
  void processIncomingEvent(T event);

  U getKind();
}
