package com.prosilion.enumtypesexperiment.event;

import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
@EqualsAndHashCode(callSuper = false)
public class UpvoteTags {
  private final List<BaseTag> upvoteTags;

  public UpvoteTags(@NonNull AddressTag addressTag, @NonNull PubKeyTag pubKeyTag) {
    this.upvoteTags = new ArrayList<>();
    this.upvoteTags.add(addressTag);
    this.upvoteTags.add(pubKeyTag);
  }
}

