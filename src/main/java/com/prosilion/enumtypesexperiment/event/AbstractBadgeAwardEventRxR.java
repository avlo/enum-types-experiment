package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public abstract class AbstractBadgeAwardEventRxR<T extends Type> implements AbstractBadgeAwardEventIFRxR<T> {
  private final Kind kind;
  private final T type;
  private final GenericEventRecord genericEventRecord;

  public AbstractBadgeAwardEventRxR(
      @NonNull T type,
      @NonNull Identity identity, @NonNull Kind kind, @NonNull List<BaseTag> tags, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    this.genericEventRecord = GenericEventEntityFactory.createInstance(identity, kind, tags, content);
    this.kind = Kind.BADGE_AWARD_EVENT;
    this.type = type;
  }
}
