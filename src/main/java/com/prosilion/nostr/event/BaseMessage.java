package com.prosilion.nostr.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;

@Getter
public abstract class BaseMessage {
    private final Command command;

    protected BaseMessage(Command command) {
        this.command = command;
    }

    public abstract String encode() throws JsonProcessingException;
}
