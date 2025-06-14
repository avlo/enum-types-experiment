package com.prosilion.nostr.event;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public abstract class AbstractBadgeAwardEvent<T extends Type> extends GenericEventEntity {
  private final Kind kind;
  private final T type;

  public AbstractBadgeAwardEvent(
      @NonNull T type,
      @NonNull Identity identity, @NonNull Kind kind, @NonNull List<BaseTag> tags, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super(identity, kind, tags, content);
    this.kind = Kind.BADGE_AWARD_EVENT;
    this.type = type;
  }

  public abstract void doSomething();
}
