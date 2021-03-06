package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPMessageType;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.AssistRequestInstructionsRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.IPSSPCapabilities;
import org.restcomm.protocols.ss7.cap.isup.DigitsImpl;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.IPSSPCapabilitiesImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class AssistRequestInstructionsRequestImpl extends CircuitSwitchedCallMessageImpl implements AssistRequestInstructionsRequest {

    public static final int _ID_correlationID = 0;
    public static final int _ID_iPSSPCapabilities = 2;
    public static final int _ID_extensions = 3;

    public static final String _PrimitiveName = "AssistRequestInstructionsRequestIndication";

    private Digits correlationID;
    private IPSSPCapabilities iPSSPCapabilities;
    private CAPExtensions extensions;

    public AssistRequestInstructionsRequestImpl() {
    }

    public AssistRequestInstructionsRequestImpl(Digits correlationID, IPSSPCapabilities ipSSPCapabilities,
            CAPExtensions extensions) {
        this.correlationID = correlationID;
        this.iPSSPCapabilities = ipSSPCapabilities;
        this.extensions = extensions;
    }

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.assistRequestInstructions_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.assistRequestInstructions;
    }

    @Override
    public Digits getCorrelationID() {
        return correlationID;
    }

    @Override
    public IPSSPCapabilities getIPSSPCapabilities() {
        return iPSSPCapabilities;
    }

    @Override
    public CAPExtensions getExtensions() {
        return extensions;
    }

    @Override
    public int getTag() throws CAPException {
        return Tag.SEQUENCE;
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

        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    CAPParsingComponentExceptionReason.MistypedParameter);
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
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {

        this.correlationID = null;
        this.iPSSPCapabilities = null;
        this.extensions = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_correlationID:
                        this.correlationID = new DigitsImpl();
                        ((DigitsImpl) this.correlationID).decodeAll(ais);
                        this.correlationID.setIsGenericNumber();
                        break;
                    case _ID_iPSSPCapabilities:
                        this.iPSSPCapabilities = new IPSSPCapabilitiesImpl();
                        ((IPSSPCapabilitiesImpl) this.iPSSPCapabilities).decodeAll(ais);
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

        if (this.correlationID == null || this.iPSSPCapabilities == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": parameters correlationID and iPSSPCapabilities are mandatory but not found",
                    CAPParsingComponentExceptionReason.MistypedParameter);
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

        if (this.correlationID == null || this.iPSSPCapabilities == null)
            throw new CAPException("Error while encoding " + _PrimitiveName
                    + ": correlationID and iPSSPCapabilities must not be null");

        ((DigitsImpl) this.correlationID).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_correlationID);
        ((IPSSPCapabilitiesImpl) this.iPSSPCapabilities).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_iPSSPCapabilities);

        if (this.extensions != null)
            ((CAPExtensionsImpl) this.extensions).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_extensions);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        this.addInvokeIdInfo(sb);

        if (this.correlationID != null) {
            sb.append(", correlationID=");
            sb.append(correlationID.toString());
        }
        if (this.iPSSPCapabilities != null) {
            sb.append(", iPSSPCapabilities=");
            sb.append(iPSSPCapabilities.toString());
        }
        if (this.extensions != null) {
            sb.append(", extensions=");
            sb.append(extensions.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
