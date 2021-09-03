package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 * AbsentSubscriberReason ::= ENUMERATED { imsiDetach (0), restrictedArea (1), noPageResponse (2), ... , purgedMS (3)} --
 * exception handling: at reception of other values than the ones listed the -- AbsentSubscriberReason shall be ignored. -- The
 * AbsentSubscriberReason: purgedMS is defined for the Super-Charger feature -- (see TS 23.116). If this value is received in a
 * Provide Roaming Number response -- it shall be mapped to the AbsentSubscriberReason: imsiDetach in the Send Routeing --
 * Information response
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum AbsentSubscriberReason {

    imsiDetach(0), restrictedArea(1), noPageResponse(2), purgedMS(3);

    private int code;

    private AbsentSubscriberReason(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AbsentSubscriberReason getInstance(int code) {
        switch (code) {
            case 0:
                return imsiDetach;
            case 1:
                return restrictedArea;
            case 2:
                return noPageResponse;
            case 3:
                return purgedMS;
            default:
                return null;
        }
    }

}
