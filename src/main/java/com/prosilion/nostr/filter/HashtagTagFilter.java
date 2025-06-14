package com.prosilion.nostr.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.prosilion.nostr.event.GenericEventDto;
import com.prosilion.nostr.event.HashtagTag;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class HashtagTagFilter<T extends HashtagTag> extends AbstractFilterable<T> {
  public final static String FILTER_KEY = "#t";

  public HashtagTagFilter(T hashtagTag) {
    super(hashtagTag, FILTER_KEY);
  }

  @Override
  public Predicate<GenericEventDto> getPredicate() {
    return (genericEvent) ->
        Filterable.getTypeSpecificTags(HashtagTag.class, genericEvent).stream().anyMatch(hashtagTag ->
            hashtagTag.getHashTag().equals(getFilterableValue()));
  }

  @Override
  public String getFilterableValue() {
    return getHashtagTag().getHashTag();
  }

  private T getHashtagTag() {
    return super.getFilterable();
  }

  public static Function<JsonNode, Filterable> fxn = node -> new HashtagTagFilter<>(new HashtagTag(node.asText()));
}
