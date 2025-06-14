package com.prosilion.nostr.service.plugin;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.event.TextNoteEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TextNoteEventKindPluginRxR<T extends TextNoteEvent> implements EventKindPluginRxRIF<T> {
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
