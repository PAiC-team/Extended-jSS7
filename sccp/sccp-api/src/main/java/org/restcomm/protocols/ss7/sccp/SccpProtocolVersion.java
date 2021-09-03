
package org.restcomm.protocols.ss7.sccp;

/**
 *
 * @author sergey vetyutnev
 *
 */
public enum SccpProtocolVersion {

    ITU(1), ANSI(2);

    private int value;

    private SccpProtocolVersion(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public static SccpProtocolVersion valueOf(int v) {
        switch (v) {
        case 1:
            return ITU;
        case 2:
            return ANSI;
        default:
            return null;
        }

    }

}
