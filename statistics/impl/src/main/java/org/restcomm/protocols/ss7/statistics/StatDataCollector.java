
package org.restcomm.protocols.ss7.statistics;

import java.util.Date;

import org.restcomm.protocols.ss7.statistics.api.StatDataCollectorType;
import org.restcomm.protocols.ss7.statistics.api.StatResult;

/**
*
* @author sergey vetyutnev
*
*/
public interface StatDataCollector {

    String getCampaignName();

    StatDataCollectorType getStatDataCollectorType();

    Date getSessionStartTime();

    StatResult restartAndGet();

    void updateData(long newVal);

    void updateData(String newVal);

}
