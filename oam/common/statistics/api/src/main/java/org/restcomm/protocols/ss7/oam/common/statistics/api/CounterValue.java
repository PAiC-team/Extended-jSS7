
package org.restcomm.protocols.ss7.oam.common.statistics.api;

import java.io.Serializable;


/**
*
* Value for one counter of one object
*
* @author sergey vetyutnev
*
*/
public interface CounterValue extends Serializable {

    /**
     * Definition of a counter
     * @return
     */
    CounterDef getCounterDef();

    /**
     * ObjectName is very important - if there are more then one object that
     * supplies statistic we must provide unique ObjectName for each object. Not
     * good example: several TCAP stacks
     *
     * @return
     */
    String getObjectName();

    /**
     * @return Long value
     */
    long getLongValue();

    /**
     * @return Double value
     */
    double getDoubleValue();

    /**
     * A set of complex values (a set of pairs "String" - "Long", for example a count of different ACN's)
     * @return
     */
    ComplexValue[] getComplexValue();

}
