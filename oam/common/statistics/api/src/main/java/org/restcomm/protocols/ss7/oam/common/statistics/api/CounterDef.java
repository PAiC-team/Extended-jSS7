
package org.restcomm.protocols.ss7.oam.common.statistics.api;

import java.io.Serializable;


/**
*
* A definition of one counter for collecting
*
* @author sergey vetyutnev
*
*/
public interface CounterDef extends Serializable {

    /**
     * CounterName is a short abbreviation, some sort of counter Id. Avoid to keep spaces there
     * CounterName (and CounterType) must not be changed till the meaning of a counter has not changed
     * CounterName must be changed when the meaning of a counter has changed
     * @return
     */
    String getCounterName();

    /**
     * Description may be a long verbal description and may be updated
     * @return
     */
    String getDescription();

    /**
     * A type of a counter (for example summory, minimal or maximal value or may be complex value)
     * @return
     */
    CounterType getCounterType();

}
