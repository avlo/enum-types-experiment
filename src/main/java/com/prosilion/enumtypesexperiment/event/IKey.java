
package com.prosilion.enumtypesexperiment.event;

import java.io.Serializable;

public interface IKey extends Serializable {

    byte[] getRawData();

    String toBech32String();
}
