package com.prosilion.enumtypesexperiment.service.plugin;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.event.TextNoteEvent;
import com.prosilion.enumtypesexperiment.event.TextNoteEventRxR;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TextNoteEventKindPluginRxR<T extends TextNoteEventRxR> implements EventKindPluginRxRIF<T> {
  private static final Log log = LogFactory.getLog(TextNoteEventKindPluginRxR.class);

  @Override
  public void processIncomingEvent(@NonNull T event) {
    log.debug(String.format("processing incoming TEXT NOTE EVENT: [%s]", event.getKind()));
    log.info(event.getKind());
  }

  @Override
  public Kind getKind() {
    return Kind.TEXT_NOTE;
  }
}
