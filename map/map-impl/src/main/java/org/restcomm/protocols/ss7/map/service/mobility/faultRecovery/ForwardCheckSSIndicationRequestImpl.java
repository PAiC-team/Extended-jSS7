
package org.restcomm.protocols.ss7.map.service.mobility.faultRecovery;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.faultRecovery.ForwardCheckSSIndicationRequest;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class ForwardCheckSSIndicationRequestImpl extends MobilityMessageImpl implements ForwardCheckSSIndicationRequest {

    public static final String _PrimitiveName = "ForwardCheckSSIndicationRequest";

    public ForwardCheckSSIndicationRequestImpl() {
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.forwardCheckSSIndication_Request;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.forwardCheckSsIndication;
    }

    @Override
    public int getTag() throws MAPException {
        throw new MAPException("Encoding / decoding is not supported");
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
    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        throw new MAPParsingComponentException("Encoding / decoding is not supported", MAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        throw new MAPParsingComponentException("Encoding / decoding is not supported", MAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        throw new MAPException("Encoding / decoding is not supported");
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        throw new MAPException("Encoding / decoding is not supported");
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        throw new MAPException("Encoding / decoding is not supported");
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
