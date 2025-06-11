package com.prosilion.enumtypesexperiment;

public enum Kind {
  TEXT_NOTE(1, "text_note"),
  BADGE_AWARD_EVENT(8, "badge_award_event");

  private final int value;
  private final String name;

  Kind(int value, String name) {
    this.name = name;
    this.value = value;
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }
}
