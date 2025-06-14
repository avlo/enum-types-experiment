package com.prosilion.nostr.event;

import java.util.List;

public interface IGenericElement {
  List<ElementAttribute> getAttributes();

  void addAttribute(ElementAttribute... attribute);

  void addAttributes(List<ElementAttribute> attributes);
}
