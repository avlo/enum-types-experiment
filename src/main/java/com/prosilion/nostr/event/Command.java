package com.prosilion.nostr.event;

import lombok.Getter;

@Getter
public enum Command {
    AUTH,
    EVENT,
    REQ,
    CLOSE,
    CLOSED,
    NOTICE,
    EOSE,
    OK
}
