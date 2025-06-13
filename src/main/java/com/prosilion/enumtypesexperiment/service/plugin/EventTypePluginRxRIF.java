package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEventIFRxR;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEventRxR;
import com.prosilion.enumtypesexperiment.event.Type;

public interface EventTypePluginRxRIF<S extends Type, T extends AbstractBadgeAwardEventIFRxR<S>, U extends Kind> {
  void processIncomingEvent(T event);

  U getKind();
}
