
package org.restcomm.protocols.ss7.scheduler;

/**
 * @author sergey.povarnin
 */
public interface SchedulerMBean {

    void setClock(Clock clock);

    Clock getClock();

    double getMissRate();

    long getWorstExecutionTime();

}
