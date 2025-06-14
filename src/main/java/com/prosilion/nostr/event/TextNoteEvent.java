package com.prosilion.nostr.event;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.NonNull;

@Event(name = "Text Note")
public class TextNoteEvent extends NIP01Event {
  private static final Log log = LogFactory.getLog(TextNoteEvent.class);

  public TextNoteEvent(@NonNull Identity identity, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super(identity, Kind.TEXT_NOTE, content);
  }

  public TextNoteEvent(@NonNull Identity identity, @NonNull List<BaseTag> tags, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    super(identity, Kind.TEXT_NOTE, tags, content);
  }

  public List<PubKeyTag> getRecipientPubkeyTags() {
    return this.getTags().stream()
        .filter(tag -> tag instanceof PubKeyTag)
        .map(tag -> (PubKeyTag) tag)
        .toList();
  }

  public List<PublicKey> getRecipients() {
    return this.getTags().stream()
        .filter(tag -> tag instanceof PubKeyTag)
        .map(tag -> (PubKeyTag) tag)
        .map(PubKeyTag::getPublicKey)
        .toList();
  }
}
