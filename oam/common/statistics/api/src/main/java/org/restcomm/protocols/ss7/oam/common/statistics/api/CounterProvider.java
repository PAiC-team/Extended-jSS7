
package org.restcomm.protocols.ss7.oam.common.statistics.api;

/**
* This interface provide the top level 2 statistics functionalities
* CounterProviderMBean keeps CounterCampaign list and registered CounterMedialtors,
* makes statistics aggregation and then keeps statistic data for the last calculated period.
*
* @author sergey vetyutnev
*
*/
public interface CounterProvider {

    String getName();

    /**
     * @return a list of CounterDefSet for registered CounterMedialtors
     */
    String[] getCounterDefSetList();

    /**
     * Return CounterDefSet for a definite CounterDefSet name or null if such CounterDefSet is absent
     * @param counterDefSetName
     * @return
     */
    CounterDefSet getCounterDefSet(String counterDefSetName);

    /**
     * @return a list for registered campaigns
     */
    String[] getCampaignsList();

    /**
     * Return a Campaign definition for a definite campaign name or null if such CounterCampaign is absent
     * @param campaignName
     * @return
     */
    CounterCampaign getCampaign(String campaignName);

    /**
     * creating a normal Campaign, duration - interval in minutes (only predefined
     * values are available)
     *
     * @param campaignName
     *            unique campaign name
     * @param counterSetName
     *            name of a counterSetDef. If counterSet is not present now,
     *            statistics data will be collected when this counterSet has
     *            been registered
     * @param duration
     *            Duration in minutes (possible values: 5, 10, 15, 20, 30 and 60 minutes)
     * @param outputFormat
     *            Output format (possible values: 0, 1, 2)
     *            0 - CSV
     *            1 - verbose
     *            2 - CSV and verbose
     */
    void createCampaign(String campaignName, String counterSetName, int duration, int outputFormat) throws Exception;

    /**
     * creating a short Campaign, duration - interval in seconds (only predefined
     * values are available)
     *
     * @param campaignName
     *            unique campaign name
     * @param counterSetName
     *            name of a counterSetDef. If counterSet is not present now,
     *            statistics data will be collected when this counterSet has
     *            been registered
     * @param duration
     *            Duration in minutes (possible values: 5, 10, 15, 20, 30 and 60 seconds)
     * @param outputFormat
     *            Output format (possible values: 0, 1, 2)
     *            0 - CSV
     *            1 -verbose
     *            2 - CSV and verbose
     */
    void createShortCampaign(String campaignName, String counterSetName, int duration, int outputFormat) throws Exception;

    /**
     * destroying a Campaign
     * @param campaignName
     */
    void destroyCampaign(String campaignName) throws Exception;

    /**
     * Get the last calculated values for a campaign. May be null if no data has been calculated.
     * @param campaignName
     * @return
     */
    CounterValueSet getLastCounterValues(String campaignName);

}
