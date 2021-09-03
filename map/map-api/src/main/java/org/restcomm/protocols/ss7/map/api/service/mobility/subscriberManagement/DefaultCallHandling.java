
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 DefaultCallHandling ::= ENUMERATED { continueCall (0) , releaseCall (1) , ...} -- exception handling: -- reception of values
 * in range 2-31 shall be treated as "continueCall" -- reception of values greater than 31 shall be treated as "releaseCall"
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum DefaultCallHandling {
    continueCall(0), releaseCall(1);

    private int code;

    private DefaultCallHandling(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static DefaultCallHandling getInstance(int code) {
        switch (code) {
            case 0:
                return DefaultCallHandling.continueCall;
            case 1:
                return DefaultCallHandling.releaseCall;
            default:
                return null;
        }
    }
}
