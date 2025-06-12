package com.prosilion.enumtypesexperiment.service;

import com.prosilion.enumtypesexperiment.event.GenericEventEntityIF;

public interface EventKindServiceIF<T extends GenericEventEntityIF> {
  void processIncomingEvent(T event);
}
