package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent.Type;
import com.prosilion.enumtypesexperiment.event.BadgeAwardDownvoteEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class BadgeAwardDownvoteEventTypePlugin<
    T extends BadgeAwardDownvoteEvent,
    U extends Kind,
    V extends Type> implements AbstractEventTypePluginIF<T, U, V> {
  private static final Log log = LogFactory.getLog(BadgeAwardDownvoteEventTypePlugin.class);

  @Override
  public void processIncomingEvent(T event) {
    log.info("\ninfo processIncomingEvent 444444444444444444444444");
    log.info("info processIncomingEvent 444444444444444444444444");
    log.debug(String.format("processing incoming DOWNVOTE EVENT: [%s]", event.getKind()));
    event.doSomething();
    log.info("info processIncomingEvent 444444444444444444444444");
    log.info("info processIncomingEvent 444444444444444444444444\n");
  }

  @Override
  public U getKind() {
    return (U) Kind.BADGE_AWARD_EVENT;
  }

  @Override
  public V getType() {
    return (V) Type.DOWNVOTE;
  }
}
