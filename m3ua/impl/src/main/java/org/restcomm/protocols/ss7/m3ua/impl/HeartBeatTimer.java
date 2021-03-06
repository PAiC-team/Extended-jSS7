
package org.restcomm.protocols.ss7.m3ua.impl;

import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.impl.message.aspsm.HeartbeatImpl;
import org.restcomm.protocols.ss7.m3ua.impl.scheduler.M3UATask;
import org.restcomm.protocols.ss7.m3ua.message.aspsm.Heartbeat;

/**
 * @author Amit Bhayani
 *
 */
public class HeartBeatTimer extends M3UATask {

    private static final Logger logger = Logger.getLogger(HeartBeatTimer.class);

    private static final int HEART_BEAT_ACK_MISSED_ALLOWED = 2;

    private static final Heartbeat HEART_BEAT = new HeartbeatImpl();

    private volatile long lastM3UAMessageTime = 0L;
    private volatile int heartBeatAckMissed = 0;

    private AspFactoryImpl aspFactoryImpl = null;

    /**
     *
     */
    public HeartBeatTimer(AspFactoryImpl aspFactoryImpl) {
        this.aspFactoryImpl = aspFactoryImpl;
    }

    protected void reset() {
        this.lastM3UAMessageTime = System.currentTimeMillis();
        this.heartBeatAckMissed = 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.m3ua.impl.scheduler.M3UATask#tick(long)
     */
    @Override
    public void tick(long now) {
        if (now - this.lastM3UAMessageTime >= this.aspFactoryImpl.m3UAManagementImpl.getHeartbeatTime()) {
            this.lastM3UAMessageTime = now;
            this.heartBeatAckMissed++;

            this.aspFactoryImpl.write(HEART_BEAT);
        }

        if (this.heartBeatAckMissed > HEART_BEAT_ACK_MISSED_ALLOWED) {
            logger.warn(String
                    .format("HEART_BEAT ACK missed %d is greater than configured %d for AspFactory %s. Underlying Association will be stopped and started again",
                            this.heartBeatAckMissed, HEART_BEAT_ACK_MISSED_ALLOWED, this.aspFactoryImpl.getName()));
            try {
                this.aspFactoryImpl.transportManagement.stopAssociation(this.aspFactoryImpl.associationName);
            } catch (Exception e) {
                logger.warn(String.format("Error while trying to stop underlying Association for AspFactpry=%s",
                        this.aspFactoryImpl.getName()), e);
            }

            try {
                this.aspFactoryImpl.transportManagement.startAssociation(this.aspFactoryImpl.associationName);
            } catch (Exception e) {
                logger.error(String.format("Error while trying to start underlying Association for AspFactpry=%s",
                        this.aspFactoryImpl.getName()), e);
            }

            // finally cancel
            this.cancel();
        }
    }
}
