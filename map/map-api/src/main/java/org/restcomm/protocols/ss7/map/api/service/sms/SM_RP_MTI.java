package org.restcomm.protocols.ss7.map.api.service.sms;

/**
 *
 * SM-RP-MTI ::= INTEGER (0..10) -- 0 SMS Deliver -- 1 SMS Status Report -- other values are reserved for future use and shall
 * be discarded if -- received
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum SM_RP_MTI {

    SMS_Deliver(0), SMS_Status_Report(1);

    private int code;

    private SM_RP_MTI(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static SM_RP_MTI getInstance(int code) {
        switch (code) {
            case 0:
                return SMS_Deliver;
            case 1:
                return SMS_Status_Report;
            default:
                return null;
        }
    }

}
