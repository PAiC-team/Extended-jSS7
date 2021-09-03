package org.restcomm.protocols.ss7.inap.api.primitives;

/**
 *
<code>
CriticalityType ::= ENUMERATED {ignore(0), abort(1)}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CriticalityType {
    ignore(0), abort(1);

    private int code;

    private CriticalityType(int code) {
        this.code = code;
    }

    public static CriticalityType getInstance(int code) {
        switch (code) {
            case 0:
                return CriticalityType.ignore;
            case 1:
                return CriticalityType.abort;
        }

        return null;
    }

    public int getCode() {
        return code;
    }
}
