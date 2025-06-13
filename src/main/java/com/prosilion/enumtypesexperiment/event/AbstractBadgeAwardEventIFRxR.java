package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;

public interface AbstractBadgeAwardEventIFRxR<T extends Type> {
  void doSomething();
  Kind getKind();
  T getType();
}
