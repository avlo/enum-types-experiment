package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.GenericEventEntity;

public interface EventKindPluginIF<T extends GenericEventEntity> {
  void processIncomingEvent(T event);

  Kind getKind();
}
