
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 *
 RequestedCAMEL-SubscriptionInfo ::= ENUMERATED { o-CSI (0), t-CSI (1), vt-CSI (2), tif-CSI (3), gprs-CSI (4), mo-sms-CSI (5),
 * ss-CSI (6), m-CSI (7), d-csi (8)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum RequestedCAMELSubscriptionInfo {

    oCSI(0), tCSI(1), vtCSI(2), tifCSI(3), gprsCSI(4), moSmsCSI(5), ssCSI(6), mCSI(7), dcsi(8);

    private int code;

    private RequestedCAMELSubscriptionInfo(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static RequestedCAMELSubscriptionInfo getInstance(int code) {
        switch (code) {
            case 0:
                return RequestedCAMELSubscriptionInfo.oCSI;
            case 1:
                return RequestedCAMELSubscriptionInfo.tCSI;
            case 2:
                return RequestedCAMELSubscriptionInfo.vtCSI;
            case 3:
                return RequestedCAMELSubscriptionInfo.tifCSI;
            case 4:
                return RequestedCAMELSubscriptionInfo.gprsCSI;
            case 5:
                return RequestedCAMELSubscriptionInfo.moSmsCSI;
            case 6:
                return RequestedCAMELSubscriptionInfo.ssCSI;
            case 7:
                return RequestedCAMELSubscriptionInfo.mCSI;
            case 8:
                return RequestedCAMELSubscriptionInfo.dcsi;
            default:
                return null;
        }
    }
}
