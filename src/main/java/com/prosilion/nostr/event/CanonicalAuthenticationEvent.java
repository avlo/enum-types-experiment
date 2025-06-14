package com.prosilion.nostr.event;

import com.prosilion.nostr.Kind;
import com.prosilion.nostr.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.lang.NonNull;

@Event(name = "Canonical authentication event", nip = 42)
public class CanonicalAuthenticationEvent extends BaseEvent {

  public CanonicalAuthenticationEvent(@NonNull Identity identity, @NonNull String challenge, @NonNull Relay relay) throws NostrException, NoSuchAlgorithmException {
    super(identity, Kind.CLIENT_AUTH,
        List.of(
            GenericTag.create("challenge", challenge),
            GenericTag.create("relay", relay.getUri())));
  }
}
