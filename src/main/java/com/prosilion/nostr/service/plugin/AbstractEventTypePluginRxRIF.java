package com.prosilion.nostr.service.plugin;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.AbstractBadgeAwardEventIFRxR;
import com.prosilion.nostr.event.Type;

public interface AbstractEventTypePluginRxRIF<T extends Kind, U extends Type, V extends AbstractBadgeAwardEventIFRxR<U>> extends EventTypePluginRxRIF<U, V, T> {
  U getType();
}
