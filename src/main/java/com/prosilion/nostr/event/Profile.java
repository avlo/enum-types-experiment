package com.prosilion.nostr.event;

import java.net.URL;
import java.util.Optional;

public interface Profile {
    String getName();
    Optional<String> getAbout();
    Optional<URL> getPicture();
}
