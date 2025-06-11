package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TextNoteEvent extends AbstractBadgeAwardEvent {
  private static final Log log = LogFactory.getLog(TextNoteEvent.class);

  public TextNoteEvent() {
    super(Kind.TEXT_NOTE, Type.REPUTATION);
  }

  @Override
  public void doSomething() {
    log.debug("TEXT NOTE EVENT");
  }
}
