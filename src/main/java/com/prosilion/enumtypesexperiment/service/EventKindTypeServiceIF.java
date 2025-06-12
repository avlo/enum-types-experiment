package com.prosilion.enumtypesexperiment.service;

import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.Type;

public interface EventKindTypeServiceIF<T extends Type, U extends AbstractBadgeAwardEvent<T>> {
  void processIncomingEvent(U event);
}
