
package org.restcomm.protocols.ss7.oam.common.statistics.api;

import java.io.Serializable;


/**
* This value is supplied by CounterMediator and consumed by CounterProvider.
* This is a value of counters results for one counter and one object.
*
* @author sergey vetyutnev
*
*/
public interface SourceValueObject extends Serializable {

    /**
     * Name of an object.
     * @return
     */
    String getObjectName();

    /**
     * Long value
     * @return
     */
    long getValue();

    /**
     * First double value
     * @return
     */
    double getValueA();

    /**
     * Second double value
     * @return
     */
    double getValueB();

    /**
     * A set of complex values (a set of pairs "String" - "Long", for example a count of different ACN's)
     * @return
     */
    ComplexValue[] getComplexValue();

}
