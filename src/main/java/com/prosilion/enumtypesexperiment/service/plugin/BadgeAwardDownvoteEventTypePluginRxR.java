package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.BadgeAwardDownvoteEvent;
import com.prosilion.enumtypesexperiment.event.BadgeAwardDownvoteEventRxR;
import com.prosilion.enumtypesexperiment.event.Type;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class BadgeAwardDownvoteEventTypePluginRxR<
    U extends Kind,
    V extends Type,
    T extends BadgeAwardDownvoteEventRxR<V>>
    implements AbstractEventTypePluginRxRIF<U, V, T> {
  private static final Log log = LogFactory.getLog(BadgeAwardDownvoteEventTypePluginRxR.class);

  @Override
  public void processIncomingEvent(T event) {
    log.debug(String.format("processing incoming DOWNVOTE EVENT: [%s]", event.getKind()));
    event.doSomething();
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
