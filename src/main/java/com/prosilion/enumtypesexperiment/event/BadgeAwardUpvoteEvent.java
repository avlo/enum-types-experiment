package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent.Type;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BadgeAwardUpvoteEvent extends AbstractBadgeAwardEvent<Kind, Type> {
  private static final Log log = LogFactory.getLog(BadgeAwardUpvoteEvent.class);

  public BadgeAwardUpvoteEvent() {
    super(Kind.BADGE_AWARD_EVENT, Type.UPVOTE);
  }

  @Override
  public void doSomething() {
    log.debug("UP VOTE EVENT");
  }
}
