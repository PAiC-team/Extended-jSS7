
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 SubscriberStatus ::= ENUMERATED { serviceGranted (0), operatorDeterminedBarring (1)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum SubscriberStatus {
    serviceGranted(0), operatorDeterminedBarring(1);

    private int code;

    private SubscriberStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static SubscriberStatus getInstance(int code) {
        switch (code) {
            case 0:
                return SubscriberStatus.serviceGranted;
            case 1:
                return SubscriberStatus.operatorDeterminedBarring;
            default:
                return null;
        }
    }
}
