package com.prosilion.nostr.event;

import java.util.Objects;

public record ElementAttribute(
    String name,
    Object value) {

  public String getName() {
    return name;
  }

  public Object getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    ElementAttribute that = (ElementAttribute) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }
}
