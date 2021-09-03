
package org.restcomm.protocols.ss7.map.service.sms;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.sms.ForwardShortMessageResponse;

public class ForwardShortMessageResponseImpl extends SmsMessageImpl implements ForwardShortMessageResponse {

    public MAPMessageType getMessageType() {
        return MAPMessageType.forwardSM_Response;
    }

    public int getOperationCode() {
        return MAPOperationCode.mo_forwardSM;
    }

    public int getTag() throws MAPException {

        throw new MAPException("ForwardShortMessageResponseIndication has no MAP message primitive");
    }

    public int getTagClass() {

        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {

        return false;
    }

    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        throw new MAPParsingComponentException("ForwardShortMessageResponseIndication has no MAP message primitive",
                MAPParsingComponentExceptionReason.MistypedParameter);
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        throw new MAPParsingComponentException("ForwardShortMessageResponseIndication has no MAP message primitive",
                MAPParsingComponentExceptionReason.MistypedParameter);
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        throw new MAPException("ForwardShortMessageResponseIndication has no MAP message primitive");
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        throw new MAPException("ForwardShortMessageResponseIndication has no MAP message primitive");
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        throw new MAPException("ForwardShortMessageResponseIndication has no MAP message primitive");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ForwardShortMessageResponse [");
        if (this.getMAPDialog() != null) {
            sb.append("DialogId=").append(this.getMAPDialog().getLocalDialogId());
        }
        sb.append("]");

        return sb.toString();
    }
}
