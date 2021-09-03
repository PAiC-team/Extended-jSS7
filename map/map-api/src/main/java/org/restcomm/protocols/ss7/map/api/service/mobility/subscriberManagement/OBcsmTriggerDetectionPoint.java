
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 O-BcsmTriggerDetectionPoint ::= ENUMERATED { collectedInfo (2), ..., routeSelectFailure (4) } -- exception handling: -- For
 * O-BcsmCamelTDPData sequences containing this parameter with any -- other value than the ones listed the receiver shall ignore
 * the whole -- O-BcsmCamelTDPDatasequence. -- For O-BcsmCamelTDP-Criteria sequences containing this parameter with any -- other
 * value than the ones listed the receiver shall ignore the whole -- O-BcsmCamelTDP-Criteria sequence.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum OBcsmTriggerDetectionPoint {
    collectedInfo(2), routeSelectFailure(4);

    private int code;

    private OBcsmTriggerDetectionPoint(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static OBcsmTriggerDetectionPoint getInstance(int code) {
        switch (code) {
            case 2:
                return OBcsmTriggerDetectionPoint.collectedInfo;
            case 4:
                return OBcsmTriggerDetectionPoint.routeSelectFailure;
            default:
                return null;
        }
    }
}
