
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
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.DisconnectLegRequest;
import org.restcomm.protocols.ss7.cap.isup.CauseCapImpl;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsImpl;
import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;
import org.restcomm.protocols.ss7.inap.primitives.LegIDImpl;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Povilas Jurna
 *
 */
public class DisconnectLegRequestImpl extends CircuitSwitchedCallMessageImpl implements DisconnectLegRequest {

    public static final int _ID_legToBeReleased = 0;
    public static final int _ID_releaseCause = 1;
    public static final int _ID_extensions = 2;

    private static final String LEG_TO_BE_RELEASED = "legToBeReleased";
    private static final String RELEASE_CAUSE = "releaseCause";
    private static final String EXTENSIONS = "extensions";

    public static final String _PrimitiveName = "DisconnectlegRequestIndication";

    private LegID legToBeReleased;
    private CauseCap capReleaseCause;
    private CAPExtensions extensions;

    public DisconnectLegRequestImpl() {
    }

    public DisconnectLegRequestImpl(LegID legToBeReleased, CauseCap capReleaseCause, CAPExtensions extensions) {
        this.legToBeReleased = legToBeReleased;
        this.capReleaseCause = capReleaseCause;
        this.extensions = extensions;
    }

    public CAPMessageType getMessageType() {
        return CAPMessageType.disconnectLeg_Request;
    }

    public int getOperationCode() {
        return CAPOperationCode.disconnectLeg;
    }

    public CAPExtensions getExtensions() {
        return extensions;
    }

    public LegID getLegToBeReleased() {
        return legToBeReleased;
    }

    public CauseCap getReleaseCause() {
        return capReleaseCause;
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

        this.legToBeReleased = null;
        this.capReleaseCause = null;
        this.extensions = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                case _ID_legToBeReleased:
                    this.legToBeReleased = new LegIDImpl();
                    AsnInputStream ais2 = ais.readSequenceStream();
                    ais2.readTag();
                    ((LegIDImpl) this.legToBeReleased).decodeAll(ais2);
                    break;
                case _ID_releaseCause:
                    this.capReleaseCause = new CauseCapImpl();
                    ((CauseCapImpl) this.capReleaseCause).decodeAll(ais);
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

        if (this.legToBeReleased == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": legToBeReleased is mandatory but not found ",
                    CAPParsingComponentExceptionReason.MistypedParameter);
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

        if (this.legToBeReleased == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": legToBeReleased must not be null");

        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_legToBeReleased);
            int pos = asnOutputStream.StartContentDefiniteLength();
            ((LegIDImpl) this.legToBeReleased).encodeAll(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);

            if (this.capReleaseCause != null)
                ((CauseCapImpl) this.capReleaseCause).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_releaseCause);

            if (this.extensions != null)
                ((CAPExtensionsImpl) this.extensions).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_extensions);

        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (INAPException e) {
            throw new CAPException("INAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        this.addInvokeIdInfo(sb);

        if (this.legToBeReleased != null) {
            sb.append(", legToBeReleased=");
            sb.append(legToBeReleased.toString());
        }
        if (this.capReleaseCause != null) {
            sb.append(", releaseCause=");
            sb.append(capReleaseCause.toString());
        }
        if (this.extensions != null) {
            sb.append(", extensions=");
            sb.append(extensions.toString());
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<DisconnectLegRequestImpl> CONNECT_REQUEST_XML = new XMLFormat<DisconnectLegRequestImpl>(
            DisconnectLegRequestImpl.class) {

        public void read(javolution.xml.XMLFormat.InputElement xml, DisconnectLegRequestImpl disconnectLegRequest)
                throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.read(xml, disconnectLegRequest);

            disconnectLegRequest.legToBeReleased = xml.get(LEG_TO_BE_RELEASED, LegIDImpl.class);
            disconnectLegRequest.capReleaseCause = xml.get(RELEASE_CAUSE, CauseCapImpl.class);
            disconnectLegRequest.extensions = xml.get(EXTENSIONS, CAPExtensionsImpl.class);

        }

        public void write(DisconnectLegRequestImpl disconnectLegRequest, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.write(disconnectLegRequest, xml);

            if (disconnectLegRequest.getLegToBeReleased() != null)
                xml.add((LegIDImpl) disconnectLegRequest.getLegToBeReleased(), LEG_TO_BE_RELEASED, LegIDImpl.class);
            if (disconnectLegRequest.getReleaseCause() != null)
                xml.add((CauseCapImpl) disconnectLegRequest.getReleaseCause(), RELEASE_CAUSE, CauseCapImpl.class);
            if (disconnectLegRequest.getExtensions() != null)
                xml.add((CAPExtensionsImpl) disconnectLegRequest.getExtensions(), EXTENSIONS, CAPExtensionsImpl.class);
        }
    };

}
