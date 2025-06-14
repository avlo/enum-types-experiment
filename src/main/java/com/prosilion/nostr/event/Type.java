package com.prosilion.nostr.event;

import lombok.Getter;

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
