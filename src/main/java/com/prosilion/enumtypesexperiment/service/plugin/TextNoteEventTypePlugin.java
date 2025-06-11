package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.TextNoteEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TextNoteEventTypePlugin<T extends TextNoteEvent, U extends Kind> implements EventTypePluginIF<T, U> {
  private static final Log log = LogFactory.getLog(TextNoteEventTypePlugin.class);

  @Override
  public void processIncomingEvent(@NonNull T event) {
    log.info("\ninfo processIncomingEvent 111111111111111111111111111");
    log.info("info processIncomingEvent 111111111111111111111111111");
    log.debug(String.format("processing incoming TEXT NOTE EVENT: [%s]", event.getKind()));
    event.doSomething();
    log.info("info processIncomingEvent 111111111111111111111111111");
    log.info("info processIncomingEvent 111111111111111111111111111\n");
  }

  @Override
  public U getKind() {
    return (U) Kind.TEXT_NOTE;
  }
}
