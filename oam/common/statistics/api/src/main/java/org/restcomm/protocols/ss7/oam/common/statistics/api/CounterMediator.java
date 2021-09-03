
package org.restcomm.protocols.ss7.oam.common.statistics.api;


/**
* This interface must provide the class who want to export statistic data - for example TCAPStackStat
*
* @author sergey vetyutnev
*
*/
public interface CounterMediator {

    /**
     * Must return the unique name inside the system
     * @return
     */
    String getCounterMediatorName();

    /**
     * Must return names of one or several CounterDefSet that this class provides
     * @return
     */
    String[] getCounterDefSetList();

    /**
     * Must return CounterDefSet with defined name or null if absent
     * @param counterDefSetName
     * @return
     */
    CounterDefSet getCounterDefSet(String counterDefSetName);

    /**
     * This method will provide "current value" - the value for current moment
     * for both source counter types (current values and cumulative values).
     * Aggregation task is the task for a class that implements
     * CounterCampaignProvider interface (and provides Campaigns set).
     * CounterCampaignProvider will invoke this method for each campaign at the
     * time of campaign start / end time
     *
     * @return
     */
    SourceValueSet getSourceValueSet(String counterDefSetName, String campaignName, int durationInSeconds);

}
