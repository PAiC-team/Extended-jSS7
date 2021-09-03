
package org.restcomm.protocols.ss7.statistics;

import java.util.Date;

import javolution.util.FastMap;

import org.restcomm.protocols.ss7.statistics.api.LongValue;
import org.restcomm.protocols.ss7.statistics.api.StatResult;

/**
*
* @author sergey vetyutnev
*
*/
public abstract class StatDataCollectorLongImpl extends StatDataCollectorAbstractImpl {

    protected long val;

    public StatDataCollectorLongImpl(String campaignName) {
        super(campaignName);
    }

    public StatResult restartAndGet() {
        StatResultLong statResultLong = new StatResultLong(val);
        this.sessionStartTime = new Date();
        this.reset();
        return statResultLong;
    }

    public class StatResultLong implements StatResult {

        private long val;

        public StatResultLong(long val) {
            this.val = val;
        }

        @Override
        public long getLongValue() {
            return val;
        }

        @Override
        public FastMap<String, LongValue> getStringLongValue() {
            return null;
        }

    }
}
