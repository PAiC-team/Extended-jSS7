
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
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.ApplyChargingReportRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.TimeDurationChargingResult;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.TimeDurationChargingResultImpl;

/**
 *
 * @author sergey vetyutnev
 * @author Amit Bhayani
 *
 */
public class ApplyChargingReportRequestImpl extends CircuitSwitchedCallMessageImpl implements ApplyChargingReportRequest {

    private static final String TIME_DURATION_CHARGING_RESULT = "timeDurationChargingResult";

    public static final int _ID_timeDurationChargingResult = 0;

    public static final int _ID_partyToCharge = 0;

    public static final String _PrimitiveName = "ApplyChargingReportRequestIndication";

    private TimeDurationChargingResult timeDurationChargingResult;

    public ApplyChargingReportRequestImpl() {
    }

    public ApplyChargingReportRequestImpl(TimeDurationChargingResult timeDurationChargingResult) {
        this.timeDurationChargingResult = timeDurationChargingResult;
    }

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.applyChargingReport_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.applyChargingReport;
    }

    @Override
    public TimeDurationChargingResult getTimeDurationChargingResult() {
        return timeDurationChargingResult;
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

        this.timeDurationChargingResult = null;

        byte[] buf = asnInputStream.readOctetStringData(length);
        AsnInputStream aiss = new AsnInputStream(buf);

        int tag = aiss.readTag();

        if (tag != _ID_timeDurationChargingResult || aiss.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || aiss.isTagPrimitive())
            throw new CAPParsingComponentException("Error when decoding " + _PrimitiveName
                    + ": bad tag or tagClass or is primitive of the choice timeDurationChargingResult",
                    CAPParsingComponentExceptionReason.MistypedParameter);

        this.timeDurationChargingResult = new TimeDurationChargingResultImpl();
        ((TimeDurationChargingResultImpl) this.timeDurationChargingResult).decodeAll(aiss);
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

        if (this.timeDurationChargingResult == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": timeDurationChargingResult must not be null");

        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_timeDurationChargingResult);
            int pos = asnOutputStream.StartContentDefiniteLength();
            ((TimeDurationChargingResultImpl) this.timeDurationChargingResult).encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        this.addInvokeIdInfo(sb);

        if (this.timeDurationChargingResult != null) {
            sb.append(", timeDurationChargingResult=");
            sb.append(timeDurationChargingResult.toString());
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<ApplyChargingReportRequestImpl> APPLY_CHARGING_REPORT_REQUEST_XML = new XMLFormat<ApplyChargingReportRequestImpl>(
            ApplyChargingReportRequestImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, ApplyChargingReportRequestImpl applyChargingReportRequest)
                throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.read(xml, applyChargingReportRequest);
            applyChargingReportRequest.timeDurationChargingResult = xml.get(TIME_DURATION_CHARGING_RESULT,
                    TimeDurationChargingResultImpl.class);
        }

        @Override
        public void write(ApplyChargingReportRequestImpl applyChargingReportRequest, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {

            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.write(applyChargingReportRequest, xml);

            xml.add((TimeDurationChargingResultImpl) applyChargingReportRequest.timeDurationChargingResult,
                    TIME_DURATION_CHARGING_RESULT, TimeDurationChargingResultImpl.class);
        }
    };

}
