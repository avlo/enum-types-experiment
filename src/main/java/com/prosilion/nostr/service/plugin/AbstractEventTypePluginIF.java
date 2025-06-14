package com.prosilion.nostr.service.plugin;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.AbstractBadgeAwardEventIF;
import com.prosilion.nostr.event.Type;

public interface AbstractEventTypePluginIF<T extends Kind, U extends Type, V extends AbstractBadgeAwardEventIF<U>> extends EventTypePluginIF<U, V, T> {
  U getType();
}
