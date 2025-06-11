package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent.Type;
import com.prosilion.enumtypesexperiment.event.BadgeAwardUpvoteEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class BadgeAwardUpvoteEventTypePlugin<
    T extends BadgeAwardUpvoteEvent,
    U extends Kind,
    V extends Type> implements AbstractEventTypePluginIF<T, U, V> {
  private static final Log log = LogFactory.getLog(BadgeAwardUpvoteEventTypePlugin.class);

  @Override
  public void processIncomingEvent(T event) {
    log.info("\ninfo processIncomingEvent 33333333333333333333333333");
    log.info("info processIncomingEvent 33333333333333333333333333");
    log.debug(String.format("processing incoming UPVOTE EVENT: [%s]", event.getKind()));
    event.doSomething();
    log.info("info processIncomingEvent 33333333333333333333333333");
    log.info("info processIncomingEvent 33333333333333333333333333\n");
  }

  @Override
  public U getKind() {
    return (U) Kind.BADGE_AWARD_EVENT;
  }

  @Override
  public V getType() {
    return (V) Type.UPVOTE;
  }
}
