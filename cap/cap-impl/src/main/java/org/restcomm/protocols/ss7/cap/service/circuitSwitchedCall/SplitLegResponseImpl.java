
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPMessageType;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.SplitLegResponse;

/**
 *
 * @author tamas gyorgyey
 *
 */
public class SplitLegResponseImpl extends CircuitSwitchedCallMessageImpl implements SplitLegResponse {

    private static final long serialVersionUID = 1L;

    public static final String _PrimitiveName = "SplitLegResponse";

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.splitLeg_Response;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.splitLeg;
    }

    @Override
    public int getTag() throws CAPException {
        throw new CAPException("Parameter " + _PrimitiveName + ": does not support encoding");
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        return false;
    }

    @Override
    public void decodeAll(AsnInputStream asnInputStream) throws CAPParsingComponentException {
        throw new CAPParsingComponentException("Parameter " + _PrimitiveName + ": does not support encoding",
                CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {
        throw new CAPParsingComponentException("Parameter " + _PrimitiveName + ": does not support encoding",
                CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        throw new CAPException("Parameter " + _PrimitiveName + ": does not support encoding");
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws CAPException {
        throw new CAPException("Parameter " + _PrimitiveName + ": does not support encoding");
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {
        throw new CAPException("Parameter " + _PrimitiveName + ": does not support encoding");
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        sb.append("]");

        return sb.toString();
    }
}
