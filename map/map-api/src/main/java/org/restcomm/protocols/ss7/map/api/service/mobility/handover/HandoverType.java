
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

/**
 *
 HandoverType ::= ENUMERATED { interBSS (0), intraBSS (1)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum HandoverType {

    interBSS(0), intraBSS(1);

    private int code;

    private HandoverType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static HandoverType getInstance(int code) {
        switch (code) {
            case 0:
                return HandoverType.interBSS;
            case 1:
                return HandoverType.intraBSS;
            default:
                return null;
        }
    }
}
