package org.restcomm.protocols.ss7.scheduler;

import java.util.concurrent.TimeUnit;

/**
 * Media server internal clock.
 *
 * @author kulikov
 */
public interface Clock {

    /**
     * Gets the current time.
     *
     * @return current time expressed in nanoseconds.
     */
    long getTime();

    /**
     * Gets the current time.
     *
     * @param timeUnit the time measurement units.
     * @return the value in specified units.
     */
    long getTime(TimeUnit timeUnit);

    /**
     * Gets the time measurement units.
     *
     * @return the time measurement units.
     */
    TimeUnit getTimeUnit();
}
