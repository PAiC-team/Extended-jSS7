
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

/**
 *
 PDPInitiationType ::= ENUMERATED { mSInitiated (0), networkInitiated (1) }
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum PDPInitiationType {
    msInitiated(0), networkInitiated(1);

    private int code;

    private PDPInitiationType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static PDPInitiationType getInstance(int code) {
        switch (code) {
            case 0:
                return PDPInitiationType.msInitiated;
            case 1:
                return PDPInitiationType.networkInitiated;
            default:
                return null;
        }
    }

}
