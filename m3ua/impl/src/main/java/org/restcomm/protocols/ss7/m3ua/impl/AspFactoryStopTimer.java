
package org.restcomm.protocols.ss7.m3ua.impl;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.impl.scheduler.M3UATask;

/**
 * @author amit bhayani
 *
 */
public class AspFactoryStopTimer extends M3UATask {

    private static Logger logger = Logger.getLogger(AspFactoryStopTimer.class);

    private int STOP_TIMER_TIMEOUT = 3000;

    private AspFactoryImpl aspFactoryImpl = null;

    private long initiatedTime = 0L;

    /**
     * @param aspFactoryImpl
     */
    public AspFactoryStopTimer(AspFactoryImpl aspFactoryImpl) {
        super();
        this.aspFactoryImpl = aspFactoryImpl;
        this.initiatedTime = System.currentTimeMillis();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.impl.scheduler.M3UATask#tick(long)
     */
    @Override
    public void tick(long now) {
        if (now - this.initiatedTime >= STOP_TIMER_TIMEOUT) {

            if (this.aspFactoryImpl.association.isConnected()) {
                logger.warn(String
                        .format("Asp=%s was stopped but underlying Association=%s was not stopped after TIMEOUT=%d ms. Forcing stop now",
                                this.aspFactoryImpl.getName(), this.aspFactoryImpl.association.getName(), STOP_TIMER_TIMEOUT));
                try {
                    this.aspFactoryImpl.transportManagement.stopAssociation(this.aspFactoryImpl.association.getName());
                } catch (Exception e) {
                    logger.error(String.format("Exception while trying to stop Association=%s",
                            this.aspFactoryImpl.association.getName()));
                }
            }

            // Finally cancel
            this.cancel();
        }// if(now-this.initiatedTime >= STOP_TIMER_TIMEOUT)
    }

}
