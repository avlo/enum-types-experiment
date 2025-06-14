package com.prosilion.nostr.event;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.lang.NonNull;

@Event(name = "Text Note")
public class TextNoteEvent extends BaseEvent {

  public TextNoteEvent(@NonNull Identity identity, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super(identity, Kind.TEXT_NOTE, content);
  }

  public TextNoteEvent(@NonNull Identity identity, @NonNull List<BaseTag> tags, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super(identity, Kind.TEXT_NOTE, tags, content);
  }
}
