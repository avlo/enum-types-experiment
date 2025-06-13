package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.GenericEventEntity;
import com.prosilion.enumtypesexperiment.event.GenericEventRecord;
import com.prosilion.enumtypesexperiment.event.TextNoteEventRxR;

public interface EventKindPluginRxRIF<T extends TextNoteEventRxR> {
  void processIncomingEvent(T event);

  Kind getKind();
}
