package com.prosilion.enumtypesexperiment.service;

import com.prosilion.enumtypesexperiment.event.TextNoteEventRxR;

public interface EventKindServiceRxRIF<T extends TextNoteEventRxR> {
  void processIncomingEvent(T event);
}
