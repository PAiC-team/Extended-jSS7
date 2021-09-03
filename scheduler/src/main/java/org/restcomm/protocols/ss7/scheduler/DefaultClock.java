package org.restcomm.protocols.ss7.scheduler;

import java.util.concurrent.TimeUnit;

/**
 * Clock implementation wich uses the default OS clock.
 *
 * @author kulikov
 */
public class DefaultClock implements Clock {

    /**
     * The default time unit: nanoseconds.
     */
    private TimeUnit timeUnit = TimeUnit.NANOSECONDS;

    /**
     * (Non Java-doc.)
     *
     * //@see org.mobicents.media.server.scheduler.Clock.getTime().
     */
    public long getTime() {
        return System.nanoTime();
    }

    /**
     * (Non Java-doc.)
     *
     * //@see org.mobicents.media.server.scheduler.Clock.getTimeUnit().
     */
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * (Non Java-doc.)
     *
     * //@see org.mobicents.media.server.scheduler.Clock.getTime().
     */
    public long getTime(TimeUnit timeUnit) {
        return timeUnit.convert(System.nanoTime(), this.timeUnit);
    }
}
