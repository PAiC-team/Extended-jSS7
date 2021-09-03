
package org.restcomm.protocols.ss7.cap.service.gprs;

import java.io.IOException;
import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPMessageType;
import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.RequestReportGPRSEventRequest;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEvent;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.GPRSEventImpl;
import org.restcomm.protocols.ss7.cap.service.gprs.primitive.PDPIDImpl;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class RequestReportGPRSEventRequestImpl extends GprsMessageImpl implements RequestReportGPRSEventRequest {

    public static final String _PrimitiveName = "RequestReportGPRSEventRequest";

    public static final int _ID_gprsEvent = 0;
    public static final int _ID_pdpID = 1;

    private ArrayList<GPRSEvent> gprsEvent;
    private PDPID pdpID;

    public RequestReportGPRSEventRequestImpl() {
        super();
    }

    public RequestReportGPRSEventRequestImpl(ArrayList<GPRSEvent> gprsEvent, PDPID pdpID) {
        super();
        this.gprsEvent = gprsEvent;
        this.pdpID = pdpID;
    }

    @Override
    public ArrayList<GPRSEvent> getGPRSEvent() {
        return this.gprsEvent;
    }

    @Override
    public PDPID getPDPID() {
        return this.pdpID;
    }

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.requestReportGPRSEvent_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.requestReportGPRSEvent;
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

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException,
            MAPParsingComponentException {

        this.gprsEvent = null;
        this.pdpID = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_gprsEvent:
                        if (ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".gprsEvent: Parameter gprsEvent is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);

                        GPRSEvent event = null;
                        AsnInputStream ais2 = ais.readSequenceStream();
                        this.gprsEvent = new ArrayList<GPRSEvent>();
                        while (true) {
                            if (ais2.available() == 0)
                                break;

                            int tag2 = ais2.readTag();
                            if (tag2 != Tag.SEQUENCE || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || ais2.isTagPrimitive())
                                throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + "gprsEvent: bad tag or tagClass or is primitive ",
                                        CAPParsingComponentExceptionReason.MistypedParameter);

                            event = new GPRSEventImpl();
                            ((GPRSEventImpl) event).decodeAll(ais2);
                            this.gprsEvent.add(event);
                        }

                        if (this.gprsEvent.size() < 1 || this.gprsEvent.size() > 10) {
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter gprsEvent size must be from 1 to 10, found: " + this.gprsEvent.size(),
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        }
                        break;
                    case _ID_pdpID:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".pdpID: Parameter is not primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.pdpID = new PDPIDImpl();
                        ((PDPIDImpl) this.pdpID).decodeAll(ais);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.gprsEvent == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": parameter gprsEvent is mandatory but not found", CAPParsingComponentExceptionReason.MistypedParameter);

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

        if (this.gprsEvent == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": gprsEvent must not be null");

        if (this.gprsEvent.size() < 1 || this.gprsEvent.size() > 10) {
            throw new CAPException("Error while encoding " + _PrimitiveName
                    + ": Parameter gprsEvent size must be from 1 to 10, found: " + this.gprsEvent.size());
        }

        try {

            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_gprsEvent);
            int pos = asnOutputStream.StartContentDefiniteLength();
            for (GPRSEvent event : this.gprsEvent) {
                ((GPRSEventImpl) event).encodeAll(asnOutputStream);
            }
            asnOutputStream.FinalizeContent(pos);

            if (this.pdpID != null)
                ((PDPIDImpl) this.pdpID).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_pdpID);

        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");
        this.addInvokeIdInfo(sb);

        if (this.gprsEvent != null) {
            sb.append(", gprsEvent=[");
            boolean firstItem = true;
            for (GPRSEvent be : this.gprsEvent) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(be.toString());
            }
            sb.append("]");
        }

        if (this.pdpID != null) {
            sb.append(", pdpID=");
            sb.append(this.pdpID.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
