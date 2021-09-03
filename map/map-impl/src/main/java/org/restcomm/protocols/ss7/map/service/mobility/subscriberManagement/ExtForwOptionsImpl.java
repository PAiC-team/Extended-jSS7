
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtForwOptions;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtForwOptionsForwardingReason;
import org.restcomm.protocols.ss7.map.primitives.OctetStringBase;

/**
 *
 * @author daniel bichara
 * @author sergey vetyutnev
 *
 */
public class ExtForwOptionsImpl extends OctetStringBase implements ExtForwOptions {

    private static int _MASK_NotificationToForwardingParty = 0x80;
    private static int _MASK_RedirectingPresentation = 0x40;
    private static int _MASK_NotificationToCallingParty = 0x20;
    private static int _MASK_ForwardingReason = 0x0C;

    public ExtForwOptionsImpl() {
        super(1, 5, "ExtForwOptions");
    }

    public ExtForwOptionsImpl(byte[] data) {
        super(1, 5, "ExtForwOptions", data);
    }

    public ExtForwOptionsImpl(boolean notificationToForwardingParty, boolean redirectingPresentation,
            boolean notificationToCallingParty, ExtForwOptionsForwardingReason extForwOptionsForwardingReason) {
        super(1, 5, "ExtForwOptions");

        this.data = new byte[1];

        if (notificationToForwardingParty) {
            this.data[0] |= _MASK_NotificationToForwardingParty;
        }

        if (redirectingPresentation) {
            this.data[0] |= _MASK_RedirectingPresentation;
        }

        if (notificationToCallingParty) {
            this.data[0] |= _MASK_NotificationToCallingParty;
        }

        if (extForwOptionsForwardingReason != null) {
            this.data[0] |= (extForwOptionsForwardingReason.getCode() << 2);
        }
    }

    public byte[] getData() {
        return data;
    }

    public boolean getNotificationToForwardingParty() {
        /*
         * -- bit 8: notification to forwarding party -- 0 no notification -- 1 notification
         */
        if (this.data == null || this.data.length < 1)
            return false;

        return ((data[0] & _MASK_NotificationToForwardingParty) != 0 ? true : false);
    }

    public boolean getRedirectingPresentation() {
        /*
         * -- bit 7: redirecting presentation -- 0 no presentation -- 1 presentation
         */
        if (this.data == null || this.data.length < 1)
            return false;

        return ((data[0] & _MASK_RedirectingPresentation) > 0 ? true : false);
    }

    public boolean getNotificationToCallingParty() {
        /*
         * -- bit 6: notification to calling party -- 0 no notification -- 1 notification
         */
        if (this.data == null || this.data.length < 1)
            return false;

        return ((data[0] & _MASK_NotificationToCallingParty) > 0 ? true : false);
    }

    public ExtForwOptionsForwardingReason getExtForwOptionsForwardingReason() {
        if (this.data == null || this.data.length < 1)
            return null;

        return ExtForwOptionsForwardingReason.getInstance((int) ((data[0] & _MASK_ForwardingReason) >> 2));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.getNotificationToForwardingParty()) {
            sb.append("notificationToForwardingParty, ");
        }
        if (this.getRedirectingPresentation()) {
            sb.append("redirectingPresentation, ");
        }
        if (this.getNotificationToCallingParty()) {
            sb.append("notificationToCallingParty, ");
        }
        sb.append("ExtForwOptionsForwardingReason=");
        sb.append(getExtForwOptionsForwardingReason());

        sb.append("]");

        return sb.toString();
    }
}
