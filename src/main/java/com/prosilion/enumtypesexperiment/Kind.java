package com.prosilion.enumtypesexperiment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.temporal.ValueRange;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Kind {
  TEXT_NOTE(1, "text_note"),
  BADGE_AWARD_EVENT(8, "badge_award_event");

  @JsonValue
  private final int value;

  private final String name;

  @JsonCreator
  public static Kind valueOf(int value) {
    if (!ValueRange.of(0, 65_535).isValidIntValue(value)) {
      throw new IllegalArgumentException(String.format("Kind must be between 0 and 65535 but was [%d]", value));
    }
    for (Kind k : values()) {
      if (k.getValue() == value) {
        return k;
      }
    }

    return TEXT_NOTE;
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }
}
