
package org.restcomm.protocols.ss7.cap.service.sms;

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
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.sms.RequestReportSMSEventRequest;
import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.SMSEvent;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsImpl;
import org.restcomm.protocols.ss7.cap.service.sms.primitive.SMSEventImpl;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class RequestReportSMSEventRequestImpl extends SmsMessageImpl implements RequestReportSMSEventRequest {

    public static final String _PrimitiveName = "RequestReportSMSEventRequest";

    public static final int _ID_smsEvents = 0;
    public static final int _ID_extensions = 10;

    private ArrayList<SMSEvent> smsEvents;
    private CAPExtensions extensions;

    public RequestReportSMSEventRequestImpl(ArrayList<SMSEvent> smsEvents, CAPExtensions extensions) {
        super();
        this.smsEvents = smsEvents;
        this.extensions = extensions;
    }

    public RequestReportSMSEventRequestImpl() {
        super();
    }

    @Override
    public ArrayList<SMSEvent> getSMSEvents() {
        return this.smsEvents;
    }

    @Override
    public CAPExtensions getExtensions() {
        return this.extensions;
    }

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.requestReportSMSEvent_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.requestReportSMSEvent;
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
            throw new CAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new CAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": "
                    + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        } catch (MAPParsingComponentException e) {
            throw new CAPParsingComponentException("MAPParsingComponentException when decoding " + _PrimitiveName
                    + ": " + e.getMessage(), e, CAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
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
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException,
            AsnException, MAPParsingComponentException {

        this.smsEvents = null;
        this.extensions = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                case _ID_smsEvents:
                    if (ais.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".smsEvents: Parameter gprsEvent is primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);

                    SMSEvent event = null;
                    AsnInputStream ais2 = ais.readSequenceStream();
                    this.smsEvents = new ArrayList<SMSEvent>();
                    while (true) {
                        if (ais2.available() == 0)
                            break;

                        int tag2 = ais2.readTag();
                        if (tag2 != Tag.SEQUENCE || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || ais2.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + "smsEvents: bad tag or tagClass or is primitive ",
                                    CAPParsingComponentExceptionReason.MistypedParameter);

                        event = new SMSEventImpl();
                        ((SMSEventImpl) event).decodeAll(ais2);
                        this.smsEvents.add(event);
                    }

                    if (this.smsEvents.size() < 1 || this.smsEvents.size() > 10) {
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Parameter smsEvents size must be from 1 to 10, found: " + this.smsEvents.size(),
                                CAPParsingComponentExceptionReason.MistypedParameter);
                    }
                    break;
                case _ID_extensions:
                    if (ais.isTagPrimitive())
                        throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".extensions: Parameter is primitive",
                                CAPParsingComponentExceptionReason.MistypedParameter);
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

        if (this.smsEvents == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": parameter smsEvents is mandatory but not found",
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

        if (this.smsEvents == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": gprsEvent must not be null");

        if (this.smsEvents.size() < 1 || this.smsEvents.size() > 10) {
            throw new CAPException("Error while encoding " + _PrimitiveName
                    + ": Parameter gprsEvent size must be from 1 to 10, found: " + this.smsEvents.size());
        }

        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_smsEvents);
            int pos = asnOutputStream.StartContentDefiniteLength();
            for (SMSEvent event : this.smsEvents) {
                ((SMSEventImpl) event).encodeAll(asnOutputStream);
            }
            asnOutputStream.FinalizeContent(pos);

            if (this.extensions != null)
                ((CAPExtensionsImpl) this.extensions).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_extensions);

        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");
        this.addInvokeIdInfo(sb);

        if (this.smsEvents != null) {
            sb.append(", smsEvents=[");
            int i1 = 0;
            for (SMSEvent evt : this.smsEvents) {
                if (i1 == 0)
                    i1 = 1;
                else
                    sb.append(", ");
                sb.append("smsEvent=");
                sb.append(evt.toString());
            }
            sb.append("]");
        }

        if (this.extensions != null) {
            sb.append(", extensions=");
            sb.append(this.extensions.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
