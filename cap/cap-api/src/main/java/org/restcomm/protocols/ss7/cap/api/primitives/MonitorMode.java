
package org.restcomm.protocols.ss7.cap.api.primitives;

/**
 *
 MonitorMode ::= ENUMERATED { interrupted (0), notifyAndContinue (1), transparent (2) } -- Indicates the event is relayed
 * and/or processed by the SSP. -- Transparent means that the gsmSSF or gprsSSF does not notify the gsmSCF of the event. -- For
 * the use of this parameter refer to the procedure descriptions in clause 11. -- For the RequestNotificationCharging operation,
 * 'interrupted' shall not be used in MonitorMode.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum MonitorMode {

    interrupted(0), notifyAndContinue(1), transparent(2);

    private int code;

    private MonitorMode(int code) {
        this.code = code;
    }

    public static MonitorMode getInstance(int code) {
        switch (code) {
            case 0:
                return MonitorMode.interrupted;
            case 1:
                return MonitorMode.notifyAndContinue;
            case 2:
                return MonitorMode.transparent;
        }

        return null;
    }

    public int getCode() {
        return code;
    }
}
