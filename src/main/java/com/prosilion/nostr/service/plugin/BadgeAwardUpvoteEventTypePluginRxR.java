package com.prosilion.nostr.service.plugin;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.BadgeAwardUpvoteEventRxR;
import com.prosilion.nostr.event.Type;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class BadgeAwardUpvoteEventTypePluginRxR<
    U extends Kind,
    V extends Type,
    T extends BadgeAwardUpvoteEventRxR<V>>
    implements AbstractEventTypePluginRxRIF<U, V, T> {
  private static final Log log = LogFactory.getLog(BadgeAwardUpvoteEventTypePluginRxR.class);

  @Override
  public void processIncomingEvent(T event) {
    log.debug(String.format("processing incoming UPVOTE EVENT: [%s]", event.getKind()));
    event.doSomething();
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
