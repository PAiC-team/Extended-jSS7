
package org.restcomm.protocols.ss7.oam.common.alarm;

/**
 *
 * Listener for created and cleared alarms from a alarm source that generates alarm messages
 *
 * @author sergey vetyutnev
 *
 */
public interface AlarmListener {

    /**
     * Occur when alarm creating or clearing
     *
     * @param alarm
     */
    void onAlarm(AlarmMessage alarm);

}
