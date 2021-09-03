
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 T-BcsmTriggerDetectionPoint ::= ENUMERATED { termAttemptAuthorized (12), ... , tBusy (13), tNoAnswer (14)} -- exception
 * handling: -- For T-BcsmCamelTDPData sequences containing this parameter with any other -- value than the ones listed above,
 * the receiver shall ignore the whole -- T-BcsmCamelTDPData sequence.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum TBcsmTriggerDetectionPoint {
    termAttemptAuthorized(12), tBusy(13), tNoAnswer(14);

    private int code;

    private TBcsmTriggerDetectionPoint(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static TBcsmTriggerDetectionPoint getInstance(int code) {
        switch (code) {
            case 12:
                return TBcsmTriggerDetectionPoint.termAttemptAuthorized;
            case 13:
                return TBcsmTriggerDetectionPoint.tBusy;
            case 14:
                return TBcsmTriggerDetectionPoint.tNoAnswer;
            default:
                return null;
        }
    }
}
