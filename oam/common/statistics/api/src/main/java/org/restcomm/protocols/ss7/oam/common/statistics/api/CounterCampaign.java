
package org.restcomm.protocols.ss7.oam.common.statistics.api;

import java.io.Serializable;


/**
*
* Counter campaign definition.
*
* @author sergey vetyutnev
*
*/
public interface CounterCampaign extends Serializable {

    /**
     * campaign name
     * @return
     */
    String getName();

    /**
     * counterSet name (string value)
     * Concrete counterSet may not be registered at campaign creation time.
     * Counters collection will be started when counterSet will be registered
     * @return
     */
    String getCounterSetName();

    /**
     * duration in minutes (for normal campaigns) or in seconds (for short
     * campaigns) (5, 10, 15, 20, 30 and 60 are possible values)
     *
     * @return
     */
    int getDuration();

    /**
     * statistics output format
     * @return
     */
    CounterOutputFormat getOutputFormat();

    /**
     * statistics output format
     * @return
     */
    int getOutputFormatInt();

    /**
     * Returns counters definitions
     * @return
     */
    CounterDefSet getCounterSet();

    /**
     * Returns counters values
     * @return
     */
    CounterValueSet getLastCounterValueSet();

    /**
     * Returns if this campaign normal (false) or short (true)
     * @return
     */
    boolean isShortCampaign();

}
