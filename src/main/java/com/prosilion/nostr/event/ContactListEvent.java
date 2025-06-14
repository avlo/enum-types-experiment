package com.prosilion.nostr.event;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.lang.NonNull;

@Event(name = "Contact List and Petnames", nip = 2)
public class ContactListEvent extends BaseEvent {
  public ContactListEvent(@NonNull Identity identity, @NonNull List<BaseTag> tags) throws NostrException, NoSuchAlgorithmException {
    super(identity, Kind.CONTACT_LIST, tags);
  }
}
