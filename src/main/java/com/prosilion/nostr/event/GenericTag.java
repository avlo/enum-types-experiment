package com.prosilion.nostr.event;

import java.util.List;
import lombok.Getter;

//@JsonSerialize(using = GenericTagSerializer.class)
public record GenericTag(
    @Getter String code,
    @Getter List<ElementAttribute> attributes) implements BaseTag {
}


