package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent.Type;
import lombok.Getter;

@Getter
public abstract class AbstractBadgeAwardEvent<T extends Kind, U extends Type> {
  private final T kind;
  private final U type;

  public AbstractBadgeAwardEvent(T kind, U type) {
    this.kind = kind;
    this.type = type;
  }

  public abstract void doSomething();

  @Getter
  public enum Type {
    UPVOTE("upvote"),
    DOWNVOTE("downvote"),
    REPUTATION("reputation");

    private final String name;

    Type(String name) {
      this.name = name;
    }
  }
}
