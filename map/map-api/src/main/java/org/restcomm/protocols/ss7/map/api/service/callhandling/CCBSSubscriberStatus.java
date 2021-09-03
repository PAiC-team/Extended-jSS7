
package org.restcomm.protocols.ss7.map.api.service.callhandling;

/**
 *
 CCBS-SubscriberStatus ::= ENUMERATED { ccbsNotIdle (0), ccbsIdle (1), ccbsNotReachable (2), ...} -- exception handling: --
 * reception of values 3-10 shall be mapped to 'ccbsNotIdle' -- reception of values 11-20 shall be mapped to 'ccbsIdle' --
 * reception of values > 20 shall be mapped to 'ccbsNotReachable'
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CCBSSubscriberStatus {

    ccbsNotIdle(0), ccbsIdle(1), ccbsNotReachable(2);

    private int code;

    private CCBSSubscriberStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CCBSSubscriberStatus getInstance(int code) {
        if (code == 0 || code >= 3 && code <= 10)
            return CCBSSubscriberStatus.ccbsNotIdle;
        else if (code == 1 || code >= 11 && code <= 20)
            return CCBSSubscriberStatus.ccbsIdle;
        else
            return CCBSSubscriberStatus.ccbsNotReachable;
    }

}
