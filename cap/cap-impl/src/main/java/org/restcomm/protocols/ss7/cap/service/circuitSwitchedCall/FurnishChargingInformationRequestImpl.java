
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
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.FurnishChargingInformationRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.FCIBCCCAMELsequence1;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.FCIBCCCAMELsequence1Impl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class FurnishChargingInformationRequestImpl extends CircuitSwitchedCallMessageImpl implements
        FurnishChargingInformationRequest {

    public static final int _ID_fCIBCCCAMELsequence1 = 0;

    public static final String _PrimitiveName = "FurnishChargingInformationIndication";

    private static final String FCIBCC_CAMEL_SEQUENCE1 = "fciBCCCAMELsequence1";

    private FCIBCCCAMELsequence1 FCIBCCCAMELsequence1;

    public FurnishChargingInformationRequestImpl() {
    }

    public FurnishChargingInformationRequestImpl(FCIBCCCAMELsequence1 FCIBCCCAMELsequence1) {
        this.FCIBCCCAMELsequence1 = FCIBCCCAMELsequence1;
    }

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.furnishChargingInformation_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.furnishChargingInformation;
    }

    @Override
    public FCIBCCCAMELsequence1 getFCIBCCCAMELsequence1() {
        return this.FCIBCCCAMELsequence1;
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

        this.FCIBCCCAMELsequence1 = null;

        byte[] buf = asnInputStream.readOctetStringData(length);
        if (buf.length < 5 || buf.length > 255)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": data length must be from 5 to 255, found: " + buf.length,
                    CAPParsingComponentExceptionReason.MistypedParameter);

        AsnInputStream ais = new AsnInputStream(buf);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_fCIBCCCAMELsequence1:
                        this.FCIBCCCAMELsequence1 = new FCIBCCCAMELsequence1Impl();
                        ((FCIBCCCAMELsequence1Impl) this.FCIBCCCAMELsequence1).decodeAll(ais);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.FCIBCCCAMELsequence1 == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": the single choice FCIBCCCAMELsequence1 is not found",
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

        if (this.FCIBCCCAMELsequence1 == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": FCIBCCCAMELsequence1 must not be null");

        AsnOutputStream aos = new AsnOutputStream();
        ((FCIBCCCAMELsequence1Impl) this.FCIBCCCAMELsequence1).encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC,
                _ID_fCIBCCCAMELsequence1);

        byte[] buf = aos.toByteArray();
        if (buf.length < 5 || buf.length > 255)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": data length must be from 5 to 255, encoded: "
                    + buf.length);

        asnOutputStream.writeOctetStringData(buf);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        this.addInvokeIdInfo(sb);

        if (this.FCIBCCCAMELsequence1 != null) {
            sb.append(", FCIBCCCAMELsequence1=");
            sb.append(FCIBCCCAMELsequence1.toString());
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<FurnishChargingInformationRequestImpl> FURNISH_CHARGING_INFORMATION_REQUEST_XML = new XMLFormat<FurnishChargingInformationRequestImpl>(
            FurnishChargingInformationRequestImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml,
                FurnishChargingInformationRequestImpl furnishChargingInformationRequest) throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.read(xml, furnishChargingInformationRequest);

            furnishChargingInformationRequest.FCIBCCCAMELsequence1 = xml.get(FCIBCC_CAMEL_SEQUENCE1,
                    FCIBCCCAMELsequence1Impl.class);
        }

        @Override
        public void write(FurnishChargingInformationRequestImpl furnishChargingInformationRequest,
                javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.write(furnishChargingInformationRequest, xml);

            if (furnishChargingInformationRequest.FCIBCCCAMELsequence1 != null)
                xml.add((FCIBCCCAMELsequence1Impl) furnishChargingInformationRequest.FCIBCCCAMELsequence1,
                        FCIBCC_CAMEL_SEQUENCE1, FCIBCCCAMELsequence1Impl.class);
        }
    };
}
