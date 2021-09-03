
package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall;

import java.io.IOException;
import java.util.ArrayList;

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
import org.restcomm.protocols.ss7.cap.api.primitives.BCSMEvent;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.RequestReportBCSMEventRequest;
import org.restcomm.protocols.ss7.cap.primitives.BCSMEventImpl;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsImpl;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.primitives.ArrayListSerializingBase;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class RequestReportBCSMEventRequestImpl extends CircuitSwitchedCallMessageImpl implements RequestReportBCSMEventRequest {

    public static final int _ID_bcsmEvents = 0;
    public static final int _ID_extensions = 2;

    public static final String _PrimitiveName = "RequestReportBCSMEventRequest";

    private static final String EXTENSIONS = "extensions";
    private static final String BCSM_EVENT = "bcsmEvent";
    private static final String BCSM_EVENT_LIST = "bcsmEventList";

    private ArrayList<BCSMEvent> bcsmEventList;
    private CAPExtensions extensions;

    public RequestReportBCSMEventRequestImpl() {
    }

    public RequestReportBCSMEventRequestImpl(ArrayList<BCSMEvent> bcsmEventList, CAPExtensions extensions) {
        this.bcsmEventList = bcsmEventList;
        this.extensions = extensions;
    }

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.requestReportBCSMEvent_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.requestReportBCSMEvent;
    }

    @Override
    public ArrayList<BCSMEvent> getBCSMEventList() {
        return bcsmEventList;
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

        this.bcsmEventList = null;
        this.extensions = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_bcsmEvents:
                        this.bcsmEventList = new ArrayList<BCSMEvent>();
                        AsnInputStream ais2 = ais.readSequenceStream();

                        while (true) {
                            if (ais2.available() == 0)
                                break;

                            int tag2 = ais2.readTag();
                            if (tag2 != Tag.SEQUENCE || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || ais2.isTagPrimitive())
                                throw new CAPParsingComponentException(
                                        "Error when decoding RequestReportBCSMEventRequest: bad tag or tagClass or is primitive of BCSMEvent element",
                                        CAPParsingComponentExceptionReason.MistypedParameter);

                            BCSMEventImpl elem = new BCSMEventImpl();
                            elem.decodeAll(ais2);
                            this.bcsmEventList.add(elem);
                        }

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

        if (this.bcsmEventList == null)
            throw new CAPParsingComponentException(
                    "Error while decoding RequestReportBCSMEventRequest: bcsmEventList is mandatory but not found ",
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

        if (this.bcsmEventList == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": bcsmEventList must not be null");
        if (this.bcsmEventList.size() < 1 || this.bcsmEventList.size() > 30)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": bcsmEventList length must be from 1 to 30");

        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_bcsmEvents);
            int pos = asnOutputStream.StartContentDefiniteLength();
            for (BCSMEvent be : this.bcsmEventList) {
                BCSMEventImpl bee = (BCSMEventImpl) be;
                bee.encodeAll(asnOutputStream);
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
        sb.append(_PrimitiveName);
        sb.append(" [");
        this.addInvokeIdInfo(sb);

        if (this.bcsmEventList != null) {
            sb.append(", bcsmEventList=[");
            boolean firstItem = true;
            for (BCSMEvent be : this.bcsmEventList) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(be.toString());
            }
            sb.append("]");
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
    protected static final XMLFormat<RequestReportBCSMEventRequestImpl> REQUEST_REPORT_BCSM_EVENT_REQUEST_XML = new XMLFormat<RequestReportBCSMEventRequestImpl>(
            RequestReportBCSMEventRequestImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml,
                RequestReportBCSMEventRequestImpl requestReportBCSMEventRequest) throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.read(xml, requestReportBCSMEventRequest);

            RequestReportBCSMEventRequest_BCSMEvent al = xml
                    .get(BCSM_EVENT_LIST, RequestReportBCSMEventRequest_BCSMEvent.class);
            if (al != null) {
                requestReportBCSMEventRequest.bcsmEventList = al.getData();
            }

            requestReportBCSMEventRequest.extensions = xml.get(EXTENSIONS, CAPExtensionsImpl.class);
        }

        @Override
        public void write(RequestReportBCSMEventRequestImpl requestReportBCSMEventRequest,
                javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            CIRCUIT_SWITCHED_CALL_MESSAGE_XML.write(requestReportBCSMEventRequest, xml);

            if (requestReportBCSMEventRequest.getBCSMEventList() != null) {
                RequestReportBCSMEventRequest_BCSMEvent al = new RequestReportBCSMEventRequest_BCSMEvent(
                        requestReportBCSMEventRequest.getBCSMEventList());
                xml.add(al, BCSM_EVENT_LIST, RequestReportBCSMEventRequest_BCSMEvent.class);
            }

            if (requestReportBCSMEventRequest.getExtensions() != null)
                xml.add((CAPExtensionsImpl) requestReportBCSMEventRequest.getExtensions(), EXTENSIONS, CAPExtensionsImpl.class);
        }
    };

    public static class RequestReportBCSMEventRequest_BCSMEvent extends ArrayListSerializingBase<BCSMEvent> {

        public RequestReportBCSMEventRequest_BCSMEvent() {
            super(BCSM_EVENT, BCSMEventImpl.class);
        }

        public RequestReportBCSMEventRequest_BCSMEvent(ArrayList<BCSMEvent> data) {
            super(BCSM_EVENT, BCSMEventImpl.class, data);
        }

    }
}
