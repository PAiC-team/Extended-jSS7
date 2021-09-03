
package org.restcomm.protocols.ss7.oam.common.statistics.api;

import java.io.Serializable;
import java.util.Date;


/**
*
* A set of counter values for one CounterDefSet
*
* @author sergey vetyutnev
*
*/
public interface CounterValueSet extends Serializable {

    /**
     * Start time of counter observing period
     * @return
     */
    Date getStartTime();

    /**
     * End time of counter observing period
     * @return
     */
    Date getEndTime();

    /**
     * Period duration in minutes
     *
     * @return
     */
    int getDuration();

    /**
     * Period duration in seconds
     *
     * @return
     */
    int getDurationSeconds();

    /**
     * A set of CounterValue
     *
     * @return
     */
    CounterValue[] getCounterValues();

}
