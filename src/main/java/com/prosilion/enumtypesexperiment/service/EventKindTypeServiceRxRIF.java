package com.prosilion.enumtypesexperiment.service;

import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEventIFRxR;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEventRxR;
import com.prosilion.enumtypesexperiment.event.Type;

public interface EventKindTypeServiceRxRIF<T extends Type, U extends AbstractBadgeAwardEventIFRxR<T>> {
  void processIncomingEvent(U event);
}
