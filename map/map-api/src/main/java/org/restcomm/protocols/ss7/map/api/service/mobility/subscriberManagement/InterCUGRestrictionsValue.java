
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 -- bits 21 -- 00 CUG only facilities -- 01 CUG with outgoing access -- 10 CUG with incoming access -- 11 CUG with both
 * outgoing and incoming access
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum InterCUGRestrictionsValue {
    CUGOnlyFacilities(0), CUGWithOutgoingAccess(1), CUGWithIncomingAccess(2), CUGWithBothOutgoingAndIncomingAccess(3);

    private int code;

    private InterCUGRestrictionsValue(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static InterCUGRestrictionsValue getInstance(int code) {
        switch (code) {
            case 0:
                return InterCUGRestrictionsValue.CUGOnlyFacilities;
            case 1:
                return InterCUGRestrictionsValue.CUGWithOutgoingAccess;
            case 2:
                return InterCUGRestrictionsValue.CUGWithIncomingAccess;
            case 3:
                return InterCUGRestrictionsValue.CUGWithBothOutgoingAndIncomingAccess;
            default:
                return null;
        }
    }
}
