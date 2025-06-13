package com.prosilion.enumtypesexperiment.service;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEventIFRxR;
import com.prosilion.enumtypesexperiment.event.Type;
import com.prosilion.enumtypesexperiment.service.plugin.AbstractEventTypePluginRxRIF;
import com.prosilion.enumtypesexperiment.service.plugin.EventTypePluginRxRIF;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class EventKindTypeServiceRxR<V extends Type, T extends AbstractBadgeAwardEventIFRxR<V>> implements EventKindTypeServiceRxRIF<V, T> {
  private final Map<Kind, Map<V, AbstractEventTypePluginRxRIF<Kind, V, T>>> abstractEventTypePluginsMapMap;

  @Autowired
  public EventKindTypeServiceRxR(List<EventTypePluginRxRIF<V, T, Kind>> eventTypePlugins) {
    abstractEventTypePluginsMapMap = eventTypePlugins.stream()
        .filter(AbstractEventTypePluginRxRIF.class::isInstance)
        .map(tuEventTypePluginIF -> (AbstractEventTypePluginRxRIF<Kind, V, T>) tuEventTypePluginIF)
        .collect(Collectors.groupingBy(AbstractEventTypePluginRxRIF::getKind, Collectors.toMap(
            AbstractEventTypePluginRxRIF::getType, Function.identity())));
  }

  @Override
  public void processIncomingEvent(@NonNull T event) {
    Map<V, AbstractEventTypePluginRxRIF<Kind, V, T>> vAbstractEventTypePluginIFMap1 = Optional.ofNullable(
        abstractEventTypePluginsMapMap.get(event.getKind())).orElseThrow();
    AbstractEventTypePluginRxRIF<Kind, V, T> tuvAbstractEventTypePluginIF = Optional.ofNullable(vAbstractEventTypePluginIFMap1.get(event.getType())).orElseThrow();
    tuvAbstractEventTypePluginIF.processIncomingEvent(event);
  }
}
