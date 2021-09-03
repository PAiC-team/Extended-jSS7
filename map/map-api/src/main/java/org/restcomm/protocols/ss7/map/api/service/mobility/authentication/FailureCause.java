
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

/**
 *
 FailureCause ::= ENUMERATED { wrongUserResponse (0), wrongNetworkSignature (1)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum FailureCause {

    wrongUserResponse(0), wrongNetworkSignature(1);

    private int code;

    private FailureCause(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static FailureCause getInstance(int code) {
        switch (code) {
            case 0:
                return FailureCause.wrongUserResponse;
            case 1:
                return FailureCause.wrongNetworkSignature;
            default:
                return null;
        }
    }
}
