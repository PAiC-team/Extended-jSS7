
package org.restcomm.protocols.ss7.map.service.sms;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.sms.AlertServiceCentreResponse;

public class AlertServiceCentreResponseImpl extends SmsMessageImpl implements AlertServiceCentreResponse {

    public int getTag() throws MAPException {

        throw new MAPException("AlertServiceCentreResponse has no MAP message primitive");
    }

    public MAPMessageType getMessageType() {
        return MAPMessageType.alertServiceCentre_Response;
    }

    public int getOperationCode() {
        return MAPOperationCode.alertServiceCentre;
    }

    public int getTagClass() {

        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return false;
    }

    public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {
        throw new MAPParsingComponentException("AlertServiceCentreResponse has no MAP message primitive",
                MAPParsingComponentExceptionReason.MistypedParameter);
    }

    public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {
        throw new MAPParsingComponentException("AlertServiceCentreResponse has no MAP message primitive",
                MAPParsingComponentExceptionReason.MistypedParameter);
    }

    public void encodeAll(AsnOutputStream asnOs) throws MAPException {
        throw new MAPException("AlertServiceCentreResponse has no MAP message primitive");
    }

    public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws MAPException {
        throw new MAPException("AlertServiceCentreResponse has no MAP message primitive");
    }

    public void encodeData(AsnOutputStream asnOs) throws MAPException {
        throw new MAPException("AlertServiceCentreResponse has no MAP message primitive");
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("AlertServiceCentreResponse [");

        if (this.getMAPDialog() != null) {
            sb.append("DialogId=").append(this.getMAPDialog().getLocalDialogId());
        }

        sb.append("]");

        return sb.toString();
    }
}
