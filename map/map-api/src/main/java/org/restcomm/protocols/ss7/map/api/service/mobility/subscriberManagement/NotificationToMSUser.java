
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 NotificationToMSUser ::= ENUMERATED { notifyLocationAllowed (0), notifyAndVerify-LocationAllowedIfNoResponse (1),
 * notifyAndVerify-LocationNotAllowedIfNoResponse (2), ..., locationNotAllowed (3) } -- exception handling: -- At reception of
 * any other value than the ones listed the receiver shall ignore -- NotificationToMSUser.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum NotificationToMSUser {
    notifyLocationAllowed(0), notifyAndVerifyLocationAllowedIfNoResponse(1), notifyAndVerifyLocationNotAllowedIfNoResponse(2), locationNotAllowed(
            3);

    private int code;

    private NotificationToMSUser(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static NotificationToMSUser getInstance(int code) {
        switch (code) {
            case 0:
                return NotificationToMSUser.notifyLocationAllowed;
            case 1:
                return NotificationToMSUser.notifyAndVerifyLocationAllowedIfNoResponse;
            case 2:
                return NotificationToMSUser.notifyAndVerifyLocationNotAllowedIfNoResponse;
            case 3:
                return NotificationToMSUser.locationNotAllowed;
            default:
                return null;
        }
    }
}
