package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;

public class BadgeAwardDownvoteEvent<T extends Type> extends AbstractBadgeAwardEvent<T> {
  private static final Log log = LogFactory.getLog(BadgeAwardDownvoteEvent.class);

  public BadgeAwardDownvoteEvent(@NonNull Identity identity, @NonNull Kind kind, @NonNull List<BaseTag> tags, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super((T) Type.DOWNVOTE, identity, kind, tags, content);
  }

  @Override
  public void doSomething() {
    log.debug("DOWN VOTE EVENT");
  }
}
