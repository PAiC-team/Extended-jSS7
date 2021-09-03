
package org.restcomm.protocols.ss7.oam.common.statistics.api;

import java.io.Serializable;

/**
*
* A single complex value. It contains of two parameters:
* - key - key string value, for example ACN
* - value - long value, it usually is a count of how many times event "key" occured
*
* @author sergey vetyutnev
*
*/
public interface ComplexValue extends Serializable {

    String getKey();

    long getValue();

}
