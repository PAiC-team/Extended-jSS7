
package org.restcomm.protocols.ss7.statistics.api;

import java.util.Date;

/**
*
* Collection of data for a whole counter data
* Contains StatDataCollector data depending on a campaign name
*
* @author sergey vetyutnev
*
*/
public interface StatCounterCollection {

    String getCounterName();

    void clearDeadCampaigns(Date lastTime);

    StatResult restartAndGet(String campaignName);

    void updateData(long newValue);

    void updateData(String newValue);

}
