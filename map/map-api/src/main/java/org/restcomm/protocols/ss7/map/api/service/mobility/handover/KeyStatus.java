
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

/**
 *
 KeyStatus ::= ENUMERATED { old (0), new (1), ...} -- exception handling: -- received values in range 2-31 shall be treated as
 * "old" -- received values greater than 31 shall be treated as "new"
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum KeyStatus {

    _old(0), _new(1);

    private int code;

    private KeyStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static KeyStatus getInstance(int code) {

        switch (code) {
            case 0:
                return KeyStatus._old;
            case 1:
                return KeyStatus._new;
            default:
                if (code >= 2 && code <= 31)
                    return KeyStatus._old;
                else
                    return KeyStatus._new;
        }
    }
}
