
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 *
 AdditionalRequestedCAMEL-SubscriptionInfo ::= ENUMERATED { mt-sms-CSI (0), mg-csi (1), o-IM-CSI (2), d-IM-CSI (3), vt-IM-CSI
 * (4), ...} -- exception handling: unknown values shall be discarded by the receiver.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum AdditionalRequestedCAMELSubscriptionInfo {

    mtSmsCSI(0), mgCsi(1), oImCSI(2), dImCSI(3), vtImCSI(4);

    private int code;

    private AdditionalRequestedCAMELSubscriptionInfo(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static AdditionalRequestedCAMELSubscriptionInfo getInstance(int code) {
        switch (code) {
            case 0:
                return AdditionalRequestedCAMELSubscriptionInfo.mtSmsCSI;
            case 1:
                return AdditionalRequestedCAMELSubscriptionInfo.mgCsi;
            case 2:
                return AdditionalRequestedCAMELSubscriptionInfo.oImCSI;
            case 3:
                return AdditionalRequestedCAMELSubscriptionInfo.dImCSI;
            case 4:
                return AdditionalRequestedCAMELSubscriptionInfo.vtImCSI;
            default:
                return null;
        }
    }
}
