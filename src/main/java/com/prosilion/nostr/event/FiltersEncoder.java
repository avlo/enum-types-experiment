package com.prosilion.nostr.event;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prosilion.nostr.filter.Filters;

public record FiltersEncoder(Filters filters) implements Encoder {

  @Override
  public String encode() {
    ObjectNode root = ENCODER_MAPPED_AFTERBURNER.createObjectNode();

    filters.getFiltersMap().forEach((key, filterableList) ->
        root.setAll(
            filterableList
                .stream()
                .map(filterable ->
                    filterable.toObjectNode(root))
                .toList()
                .getFirst()));

    return root.toString();
  }
}
