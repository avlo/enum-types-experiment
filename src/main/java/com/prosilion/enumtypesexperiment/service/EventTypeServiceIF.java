package com.prosilion.enumtypesexperiment.service;

import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;

public interface EventTypeServiceIF<T extends AbstractBadgeAwardEvent> {
  void processIncomingEvent(T event);
}
