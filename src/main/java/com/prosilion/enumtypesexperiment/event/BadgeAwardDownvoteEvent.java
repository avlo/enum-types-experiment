package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent.Type;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BadgeAwardDownvoteEvent extends AbstractBadgeAwardEvent<Kind, Type> {
  private static final Log log = LogFactory.getLog(BadgeAwardDownvoteEvent.class);

  public BadgeAwardDownvoteEvent() {
    super(Kind.BADGE_AWARD_EVENT, Type.DOWNVOTE);
  }

  @Override
  public void doSomething() {
    log.debug("DOWN VOTE EVENT");
  }
}
