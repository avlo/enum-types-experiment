package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.Type;

public interface AbstractEventTypePluginIF<T extends Kind, U extends Type, V extends AbstractBadgeAwardEvent<U>> extends EventTypePluginIF<U, V, T> {
  U getType();
}
