package com.prosilion.nostr.service;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.TextNoteEvent;
import com.prosilion.nostr.service.plugin.EventKindPluginRxRIF;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class EventKindServiceRxR<T extends TextNoteEvent> implements EventKindServiceRxRIF<T> {
  private final Map<Kind, EventKindPluginRxRIF<T>> eventTypePluginsMap;


  public EventKindServiceRxR(List<EventKindPluginRxRIF<T>> eventTypePlugins) {
    this.eventTypePluginsMap = eventTypePlugins.stream().collect(
        Collectors.toMap(EventKindPluginRxRIF::getKind, Function.identity()));
  }

  @Override
  public void processIncomingEvent(@NonNull T event) {
    Optional.ofNullable(
            eventTypePluginsMap.get(
                event.getKind()))
        .orElse(
            eventTypePluginsMap.get(Kind.TEXT_NOTE)).processIncomingEvent(event); // everything else handled as TEXT_NOTE kind
  }
}
