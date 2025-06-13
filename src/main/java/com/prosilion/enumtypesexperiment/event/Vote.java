package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Vote {
  private final List<BaseTag> voteTags;

  public Vote(@NonNull PublicKey voter, @NonNull PublicKey upvotedUser, @NonNull Type voteType) {
    IdentifierTag identifierTag = new IdentifierTag(voteType.getName());
    AddressTag addressTag = new AddressTag(
        Kind.BADGE_AWARD_EVENT,
        voter,
        identifierTag);

    this.voteTags = new ArrayList<>();
    this.voteTags.add(addressTag);
    this.voteTags.add(new PubKeyTag(upvotedUser));
  }
}

