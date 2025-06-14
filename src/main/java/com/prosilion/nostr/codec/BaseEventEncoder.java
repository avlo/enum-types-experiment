package com.prosilion.nostr.codec;

import com.prosilion.nostr.event.BaseEvent;
import com.prosilion.nostr.event.Encoder;
import com.prosilion.nostr.event.GenericEventDto;
import lombok.SneakyThrows;

public class BaseEventEncoder<T extends GenericEventDto> implements Encoder {

  private final T event;

  public BaseEventEncoder(T event) {
    this.event = event;
  }

  @Override
//    TODO: refactor all methods calling this to properly handle invalid json exception
  @SneakyThrows
  public String encode() {
    return ENCODER_MAPPED_AFTERBURNER.writeValueAsString(event);
  }
}
