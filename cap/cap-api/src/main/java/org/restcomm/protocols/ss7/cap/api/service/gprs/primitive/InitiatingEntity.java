
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

/**
 *
 InitiatingEntity ::= ENUMERATED { mobileStation (0), sgsn (1), hlr (2), ggsn (3) }
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum InitiatingEntity {
    mobileStation(0), sgsn(1), hlr(2), ggsn(3);

    private int code;

    private InitiatingEntity(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static InitiatingEntity getInstance(int code) {
        switch (code) {
            case 0:
                return InitiatingEntity.mobileStation;
            case 1:
                return InitiatingEntity.sgsn;
            case 2:
                return InitiatingEntity.hlr;
            case 3:
                return InitiatingEntity.ggsn;
            default:
                return null;
        }
    }
}
