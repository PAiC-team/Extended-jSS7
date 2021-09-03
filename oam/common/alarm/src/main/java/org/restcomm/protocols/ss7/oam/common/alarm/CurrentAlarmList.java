
package org.restcomm.protocols.ss7.oam.common.alarm;

/**
 *
 * A list of alarms that are currently active
 *
 * @author sergey vetyutnev
 *
 */
public interface CurrentAlarmList {

    /**
     * A list of alarms that are currently active
     */
    AlarmMessage[] getCurrentAlarmList();

}
