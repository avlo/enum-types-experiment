//package com.prosilion.nostr.service;
//
//import com.prosilion.nostr.Kind;
//import com.prosilion.nostr.event.AbstractBadgeAwardEvent;
//import com.prosilion.nostr.event.AbstractBadgeAwardEvent.Type;
//import com.prosilion.nostr.service.plugin.AbstractEventTypePluginIF;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EventTypeServiceRxR<T extends AbstractBadgeAwardEvent<Kind, Type>> implements EventTypeServiceIF<T> {
//  private static final Logger log = LoggerFactory.getLogger(EventTypeServiceRxR.class);
//  private final Map<Kind, List<AbstractEventTypePluginIF<T>>> eventTypePluginsMap;
//
//
//  @Autowired
//  public EventTypeServiceRxR(List<AbstractEventTypePluginIF<T>> eventTypePlugins) {
//    Collector<AbstractEventTypePluginIF<T>, ?, Map<Kind, List<AbstractEventTypePluginIF<T>>>> collector = Collectors.groupingBy(AbstractEventTypePluginIF::getKind);
//    this.eventTypePluginsMap = eventTypePlugins.stream().collect(collector);
//  }
//
//  @Override
//  public void processIncomingEvent(@NonNull T event) {
//    List<AbstractEventTypePluginIF<T>> value = eventTypePluginsMap.get(event.getKind());
//    Optional<AbstractEventTypePluginIF<T>> reduce = value.stream()
//        .mapMulti((discernibleEventTypePlugin, objectConsumer) -> discernibleEventTypePlugin.processIncomingEvent(
//            discernibleEventTypePlugin.getType(event.getType());
//            
////        .takeWhile(
////        .noneMatch(
////        .dropWhile(
////        .anyMatch(
////        .reduce((BinaryOperator<EventTypePluginIF<T>>) Function.identity());
////        .orElseGet(eventTypeList -> eventTypeList
////            reduce.orElseThrow().processIncomingEvent(event);
//
//    log.debug("adfasdfasdf");
////        .processIncomingEvent(event); // everything else handled as TEXT_NOTE kind
//  }
//}
