package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.Type;

public interface EventTypePluginIF<S extends Type, T extends AbstractBadgeAwardEvent<S>, U extends Kind> {
  void processIncomingEvent(T event);

  U getKind();
}
