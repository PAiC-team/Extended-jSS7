
package org.restcomm.protocols.ss7.cap.api.primitives;

/**
 *
 CriticalityType ::= ENUMERATED { ignore (0), abort (1) }
 *
 * @author sergey vetyutnev
 *
 */
public enum CriticalityType {
    typeIgnore(0), typeAbort(1);

    private int code;

    private CriticalityType(int code) {
        this.code = code;
    }

    public static CriticalityType getInstance(int code) {
        switch (code) {
            case 0:
                return CriticalityType.typeIgnore;
            case 1:
                return CriticalityType.typeAbort;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
