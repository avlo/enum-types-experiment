package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;

public class BadgeAwardUpvoteEventRxR<T extends Type> extends AbstractBadgeAwardEventRxR<T> {
  private static final Log log = LogFactory.getLog(BadgeAwardUpvoteEventRxR.class);

  public BadgeAwardUpvoteEventRxR(
      @NonNull Identity identity,
      @NonNull Identity upvotedUser,
      @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super((T) Type.UPVOTE, identity, Kind.BADGE_AWARD_EVENT,
        new Vote(
            identity.getPublicKey(),
            upvotedUser.getPublicKey(),
            Type.UPVOTE).getVoteTags(),
        content);
  }

  public BadgeAwardUpvoteEventRxR(
      @NonNull Identity identity,
      @NonNull Identity upvotedUser,
      @NonNull List<BaseTag> tags,
      @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super((T) Type.UPVOTE, identity, Kind.BADGE_AWARD_EVENT,
        Stream.concat(new Vote(
            identity.getPublicKey(),
            upvotedUser.getPublicKey(),
            Type.UPVOTE).getVoteTags().stream(), tags.stream()).toList()
        , content);
  }

  @Override
  public void doSomething() {
    log.debug("UP VOTE EVENT");
  }
}
