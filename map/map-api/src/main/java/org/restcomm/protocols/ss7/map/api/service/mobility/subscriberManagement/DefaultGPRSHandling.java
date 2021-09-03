
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 DefaultGPRS-Handling ::= ENUMERATED { continueTransaction (0) , releaseTransaction (1) , ...} -- exception handling: --
 * reception of values in range 2-31 shall be treated as "continueTransaction" -- reception of values greater than 31 shall be
 * treated as "releaseTransaction"
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum DefaultGPRSHandling {
    continueTransaction(0), releaseTransaction(1);

    private int code;

    private DefaultGPRSHandling(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static DefaultGPRSHandling getInstance(int code) {
        switch (code) {
            case 0:
                return DefaultGPRSHandling.continueTransaction;
            case 1:
                return DefaultGPRSHandling.releaseTransaction;
            default:
                return null;
        }
    }
}
