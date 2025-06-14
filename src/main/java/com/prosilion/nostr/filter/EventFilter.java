package com.prosilion.nostr.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.prosilion.nostr.event.GenericEventDto;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class EventFilter<T extends GenericEventDto> extends AbstractFilterable<T> {
  public final static String FILTER_KEY = "ids";

  public EventFilter(T event) {
    super(event, FILTER_KEY);
  }

  @Override
  public Predicate<GenericEventDto> getPredicate() {
    return (genericEvent) ->
        genericEvent.getId().equals(getFilterableValue());
  }

  @Override
  public String getFilterableValue() {
    return getEvent().getId();
  }

  private GenericEventDto getEvent() {
    return super.getFilterable();
  }

  public static Function<JsonNode, Filterable> fxn = node -> new EventFilter<>(new GenericEventDto(node.asText()));
}
