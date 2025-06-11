package com.prosilion.enumtypesexperiment.service;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent.Type;
import com.prosilion.enumtypesexperiment.service.plugin.AbstractEventTypePluginIF;
import com.prosilion.enumtypesexperiment.service.plugin.EventTypePluginIF;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class EventTypeService<U extends Kind, V extends Type, T extends AbstractBadgeAwardEvent<U, V>> implements EventTypeServiceIF<T> {
  private final Map<U, Map<V, AbstractEventTypePluginIF<T, U, V>>> abstractEventTypePluginsMapMap;


  @Autowired
  public EventTypeService(List<EventTypePluginIF<T, U>> eventTypePlugins) {
    abstractEventTypePluginsMapMap = eventTypePlugins.stream()
        .filter(AbstractEventTypePluginIF.class::isInstance)
        .map(tuEventTypePluginIF -> (AbstractEventTypePluginIF<T, U, V>) tuEventTypePluginIF)
        .collect(Collectors.groupingBy(AbstractEventTypePluginIF::getKind, Collectors.toMap(
            AbstractEventTypePluginIF::getType, Function.identity())));
  }

  @Override
  public void processIncomingEvent(@NonNull T event) {
    Map<V, AbstractEventTypePluginIF<T, U, V>> vAbstractEventTypePluginIFMap1 = Optional.ofNullable(
        abstractEventTypePluginsMapMap.get(event.getKind())).orElseThrow();

    AbstractEventTypePluginIF<T, U, V> tuvAbstractEventTypePluginIF = Optional.ofNullable(vAbstractEventTypePluginIFMap1.get(event.getType())).orElseThrow();

    tuvAbstractEventTypePluginIF.processIncomingEvent(event);
  }
}
