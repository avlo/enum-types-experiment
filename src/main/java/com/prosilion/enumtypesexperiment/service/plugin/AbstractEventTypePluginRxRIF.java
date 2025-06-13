package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEventIFRxR;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEventRxR;
import com.prosilion.enumtypesexperiment.event.Type;

public interface AbstractEventTypePluginRxRIF<T extends Kind, U extends Type, V extends AbstractBadgeAwardEventIFRxR<U>> extends EventTypePluginRxRIF<U, V, T> {
  U getType();
}
