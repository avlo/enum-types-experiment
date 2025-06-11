package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent.Type;

public interface AbstractEventTypePluginIF<T extends AbstractBadgeAwardEvent, U extends Kind, V extends Type> extends EventTypePluginIF<T, U> {
  V getType();
}
