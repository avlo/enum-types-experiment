package com.prosilion.nostr.event;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;

public class BadgeAwardDownvoteEventRxR<T extends Type> extends AbstractBadgeAwardEventRxR<T> {
  private static final Log log = LogFactory.getLog(BadgeAwardDownvoteEventRxR.class);

  public BadgeAwardDownvoteEventRxR(
      @NonNull Identity identity,
      @NonNull Identity upvotedUser,
      @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super((T) Type.DOWNVOTE, identity, Kind.BADGE_AWARD_EVENT,
        new Vote(
            identity.getPublicKey(),
            upvotedUser.getPublicKey(),
            Type.DOWNVOTE).getVoteTags(),
        content);
  }

  public BadgeAwardDownvoteEventRxR(
      @NonNull Identity identity,
      @NonNull Identity upvotedUser,
      @NonNull List<BaseTag> tags,
      @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super((T) Type.DOWNVOTE, identity, Kind.BADGE_AWARD_EVENT,
        Stream.concat(new Vote(
            identity.getPublicKey(),
            upvotedUser.getPublicKey(),
            Type.DOWNVOTE).getVoteTags().stream(), tags.stream()).toList()
        , content);
  }

  @Override
  public void doSomething() {
    log.debug("DOWN VOTE EVENT");
  }
}
