package org.restcomm.protocols.ss7.inap.api.primitives;

/**
 *
 <code>
MonitorMode ::= ENUMERATED {
  interrupted (0),
  notifyAndContinue (1),
  transparent (2)
}
-- Indicates the event is relayed and/or processed by the SSP.
-- If this parameter is used in the context of charging events,
-- the following definitions apply for the handling of charging events:
-- Interrupted means that the SSF notifies the SCF of the charging event using
-- EventNotificationCharging, does not process the event but discard it.
-- NotifyAndContinue means that SSF notifies the SCF of the charging event using
-- EventNotificationCharging, and continues processing the event or signal without
-- waiting for SCF instructions.
-- Transparent means that the SSF does not notify the SCF of the event.
-- This value is used to end the monitoring of a previously requested charging event.
-- Previously requested charging events are monitored
-- until ended by a transparent monitor mode, or until the end of the connection configuration.
-- For the use of this parameter in the context of BCSM events refer to clause 18.
</code>
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
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }

}
