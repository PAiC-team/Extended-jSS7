
package org.restcomm.protocols.ss7.cap.service.gprs;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPMessageType;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.ActivityTestGPRSResponse;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ActivityTestGPRSResponseImpl extends GprsMessageImpl implements ActivityTestGPRSResponse {

    public static final String _PrimitiveName = "ActivityTestGPRSResponse";

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.activityTestGPRS_Response;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.activityTestGPRS;
    }

    @Override
    public int getTag() throws CAPException {
        throw new CAPException("Parameter " + _PrimitiveName + ": does not support encoding");
    }

    @Override
    public int getTagClass() {
        return 0;
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
        this.addInvokeIdInfo(sb);

        sb.append("]");

        return sb.toString();
    }
}
