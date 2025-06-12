package com.prosilion.enumtypesexperiment.event;

public interface ISignableDto extends ISignableEntity {
    void setSignature(Signature signature);
}
