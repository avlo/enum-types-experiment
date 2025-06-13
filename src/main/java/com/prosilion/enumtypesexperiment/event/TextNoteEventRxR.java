package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.lang.NonNull;

@Event(name = "Text Note")
public class TextNoteEventRxR extends NIP01EventRxR {

  public TextNoteEventRxR(@NonNull Identity identity, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super(identity, Kind.TEXT_NOTE, content);
  }

  public TextNoteEventRxR(@NonNull Identity identity, @NonNull List<BaseTag> tags, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super(identity, Kind.TEXT_NOTE, tags, content);
  }
}
