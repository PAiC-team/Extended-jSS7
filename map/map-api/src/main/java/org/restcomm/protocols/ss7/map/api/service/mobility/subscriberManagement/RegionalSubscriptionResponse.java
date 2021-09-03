
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 RegionalSubscriptionResponse ::= ENUMERATED { networkNode-AreaRestricted (0), tooManyZoneCodes (1), zoneCodesConflict (2),
 * regionalSubscNotSupported (3)}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum RegionalSubscriptionResponse {
    networkNodeAreaRestricted(0), tooManyZoneCodes(1), zoneCodesConflict(2), regionalSubscNotSupported(3);

    private int code;

    private RegionalSubscriptionResponse(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static RegionalSubscriptionResponse getInstance(int code) {
        switch (code) {
            case 0:
                return RegionalSubscriptionResponse.networkNodeAreaRestricted;
            case 1:
                return RegionalSubscriptionResponse.tooManyZoneCodes;
            case 2:
                return RegionalSubscriptionResponse.zoneCodesConflict;
            case 3:
                return RegionalSubscriptionResponse.regionalSubscNotSupported;
            default:
                return null;
        }
    }
}
