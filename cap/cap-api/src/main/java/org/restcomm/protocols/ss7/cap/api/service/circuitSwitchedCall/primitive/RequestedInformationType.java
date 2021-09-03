
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

/**
 *
 RequestedInformationType ::= ENUMERATED { callAttemptElapsedTime (0), callStopTime (1), callConnectedElapsedTime (2),
 * releaseCause (30) }
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum RequestedInformationType {

    callAttemptElapsedTime(0), callStopTime(1), callConnectedElapsedTime(2), releaseCause(30);

    private int code;

    private RequestedInformationType(int code) {
        this.code = code;
    }

    public static RequestedInformationType getInstance(int code) {
        switch (code) {
            case 0:
                return RequestedInformationType.callAttemptElapsedTime;
            case 1:
                return RequestedInformationType.callStopTime;
            case 2:
                return RequestedInformationType.callConnectedElapsedTime;
            case 30:
                return RequestedInformationType.releaseCause;
        }

        return null;
    }

    public int getCode() {
        return code;
    }
}
