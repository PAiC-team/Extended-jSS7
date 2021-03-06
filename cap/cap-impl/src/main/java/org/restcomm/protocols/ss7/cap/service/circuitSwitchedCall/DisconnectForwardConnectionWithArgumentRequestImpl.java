
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
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.DisconnectForwardConnectionWithArgumentRequest;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsImpl;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Povilas Jurna
 *
 */
public class DisconnectForwardConnectionWithArgumentRequestImpl extends CircuitSwitchedCallMessageImpl implements DisconnectForwardConnectionWithArgumentRequest {

    public static final int _ID_callSegmentID = 1;
    public static final int _ID_extensions = 2;

    private static final String CALL_SEGMENT_ID = "callSegmentID";
    private static final String EXTENSIONS = "extensions";

    public static final String _PrimitiveName = "DisconnectForwardConnectionWithArgumentRequestIndication";

    private Integer callSegmentID;
    private CAPExtensions extensions;

    public DisconnectForwardConnectionWithArgumentRequestImpl() {
    }

    public DisconnectForwardConnectionWithArgumentRequestImpl(Integer callSegmentId, CAPExtensions extensions) {
        this.callSegmentID = callSegmentId;
        this.extensions = extensions;
    }

    public CAPMessageType getMessageType() {
        return CAPMessageType.disconnectForwardConnectionWithArgument_Request;
    }

    public int getOperationCode() {
        return CAPOperationCode.dFCWithArgument;
    }

    public int getTag() throws CAPException {
        return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return false;
    }

    public void decodeAll(AsnInputStream asnInputStream) throws CAPParsingComponentException {

        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName
                    + ": " + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (INAPParsingComponentException e) {
            throw new CAPParsingComponentException("INAPParsingComponentException when decoding " + _PrimitiveName
                    + ": " + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException {

        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName
                    + ": " + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (INAPParsingComponentException e) {
            throw new CAPParsingComponentException("INAPParsingComponentException when decoding " + _PrimitiveName
                    + ": " + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException,
            MAPParsingComponentException, INAPParsingComponentException, IOException, AsnException {

        this.callSegmentID = null;
        this.extensions = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                case _ID_callSegmentID:
                    this.callSegmentID = (int) ais.readInteger();
                    break;
                case _ID_extensions:
                    this.extensions = new CAPExtensionsImpl();
                    ((CAPExtensionsImpl) this.extensions).decodeAll(ais);
                    break;
                default:
                    ais.advanceElement();
                    break;
                }
            } else {
                ais.advanceElement();
            }
        }
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws CAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

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

    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        try {
            if (this.callSegmentID != null) {
                if (this.callSegmentID < 1 || this.callSegmentID > 127)
                    throw new CAPException("Error while encoding " + _PrimitiveName + ": callSegmentID must be from 1 to 127");
                    asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_callSegmentID, this.callSegmentID);
            }

            if (this.extensions != null)
                ((CAPExtensionsImpl) this.extensions).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_extensions);

        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        this.addInvokeIdInfo(sb);

        if (this.callSegmentID != null) {
            sb.append(", callSegmentId=");
            sb.append(callSegmentID.toString());
            sb.append(", ");
        }
        if (this.extensions != null) {
            sb.append(", extensions=");
            sb.append(extensions.toString());
            sb.append(", ");
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<DisconnectForwardConnectionWithArgumentRequestImpl> CONNECT_REQUEST_XML = new XMLFormat<DisconnectForwardConnectionWithArgumentRequestImpl>(
            DisconnectForwardConnectionWithArgumentRequestImpl.class) {

        public void read(javolution.xml.XMLFormat.InputElement xml,
                DisconnectForwardConnectionWithArgumentRequestImpl disconnectForwardConnectionWithArgumentRequest)
                throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.read(xml, disconnectForwardConnectionWithArgumentRequest);

            disconnectForwardConnectionWithArgumentRequest.callSegmentID = xml.get(CALL_SEGMENT_ID, Integer.class);
            disconnectForwardConnectionWithArgumentRequest.extensions = xml.get(EXTENSIONS, CAPExtensionsImpl.class);

        }

        public void write(
                DisconnectForwardConnectionWithArgumentRequestImpl disconnectForwardConnectionWithArgumentRequest,
                javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.write(disconnectForwardConnectionWithArgumentRequest, xml);

            if (disconnectForwardConnectionWithArgumentRequest.getCallSegmentID() != null)
                xml.add(disconnectForwardConnectionWithArgumentRequest.getCallSegmentID(), CALL_SEGMENT_ID,
                        Integer.class);
            if (disconnectForwardConnectionWithArgumentRequest.getExtensions() != null)
                xml.add((CAPExtensionsImpl) disconnectForwardConnectionWithArgumentRequest.getExtensions(), EXTENSIONS,
                        CAPExtensionsImpl.class);
        }
    };

    @Override
    public Integer getCallSegmentID() {
        return callSegmentID;
    }

    @Override
    public CAPExtensions getExtensions() {
        return extensions;
    }

}
