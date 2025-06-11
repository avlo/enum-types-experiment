package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;

public interface EventTypePluginIF<T extends AbstractBadgeAwardEvent, U extends Kind> {
  void processIncomingEvent(T event);

  U getKind();
}
