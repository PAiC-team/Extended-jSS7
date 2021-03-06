
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPMessageType;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.ReleaseCallRequest;
import org.restcomm.protocols.ss7.cap.isup.CauseCapImpl;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ReleaseCallRequestImpl extends CircuitSwitchedCallMessageImpl implements ReleaseCallRequest {

    public static final String _PrimitiveName = "ReleaseCallRequestIndication";

    private static final String CAUSE = "cause";

    private CauseCap cause;

    public ReleaseCallRequestImpl() {
    }

    public ReleaseCallRequestImpl(CauseCap cause) {
        this.cause = cause;
    }

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.releaseCall_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.releaseCall;
    }

    @Override
    public CauseCap getCause() {
        return cause;
    }

    @Override
    public int getTag() throws CAPException {
        return Tag.STRING_OCTET;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
    public boolean getIsPrimitive() {
        return true;
    }

    @Override
    public void decodeAll(AsnInputStream asnInputStream) throws CAPParsingComponentException {

        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {

        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, MAPParsingComponentException,
            IOException, AsnException {

        this.cause = new CauseCapImpl();
        ((CauseCapImpl) this.cause).decodeData(asnInputStream, length);
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws CAPException {

        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.cause == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": cause must not be null");

        ((CauseCapImpl) this.cause).encodeData(asnOutputStream);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        this.addInvokeIdInfo(sb);

        if (this.cause != null) {
            sb.append(", cause=");
            sb.append(cause.toString());
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<ReleaseCallRequestImpl> RELEASE_CALL_REQUEST_XML = new XMLFormat<ReleaseCallRequestImpl>(
            ReleaseCallRequestImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ReleaseCallRequestImpl releaseCallRequest)
                throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.read(xml, releaseCallRequest);

            releaseCallRequest.cause = xml.get(CAUSE, CauseCapImpl.class);
        }

        @Override
        public void write(ReleaseCallRequestImpl releaseCallRequest, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.write(releaseCallRequest, xml);

            if (releaseCallRequest.getCause() != null)
                xml.add((CauseCapImpl) releaseCallRequest.getCause(), CAUSE, CauseCapImpl.class);
        }
    };
}
