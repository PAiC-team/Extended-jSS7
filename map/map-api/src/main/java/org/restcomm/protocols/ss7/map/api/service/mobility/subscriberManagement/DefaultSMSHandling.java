
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 DefaultSMS-Handling ::= ENUMERATED { continueTransaction (0) , releaseTransaction (1) , ...} -- exception handling: --
 * reception of values in range 2-31 shall be treated as "continueTransaction" -- reception of values greater than 31 shall be
 * treated as "releaseTransaction"
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum DefaultSMSHandling {
    continueTransaction(0), releaseTransaction(1);

    private int code;

    private DefaultSMSHandling(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static DefaultSMSHandling getInstance(int code) {
        switch (code) {
            case 0:
                return DefaultSMSHandling.continueTransaction;
            case 1:
                return DefaultSMSHandling.releaseTransaction;
            default:
                return null;
        }
    }
}
