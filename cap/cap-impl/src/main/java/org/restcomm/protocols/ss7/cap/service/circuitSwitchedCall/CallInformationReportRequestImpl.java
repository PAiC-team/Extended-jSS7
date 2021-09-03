package org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall;

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
import org.restcomm.protocols.ss7.cap.api.primitives.ReceivingSideID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CallInformationReportRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.RequestedInformation;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsImpl;
import org.restcomm.protocols.ss7.cap.primitives.ReceivingSideIDImpl;
import org.restcomm.protocols.ss7.cap.service.circuitSwitchedCall.primitive.RequestedInformationImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CallInformationReportRequestImpl extends CircuitSwitchedCallMessageImpl implements CallInformationReportRequest {

    public static final int _ID_requestedInformationList = 0;
    public static final int _ID_extensions = 2;
    public static final int _ID_legID = 3;

    public static final String _PrimitiveName = "CallInformationReportRequestIndication";

    private ArrayList<RequestedInformation> requestedInformationList;
    private CAPExtensions extensions;
    private ReceivingSideID legID;

    public CallInformationReportRequestImpl() {
    }

    public CallInformationReportRequestImpl(ArrayList<RequestedInformation> requestedInformationList, CAPExtensions extensions,
            ReceivingSideID legID) {
        this.requestedInformationList = requestedInformationList;
        this.extensions = extensions;
        this.legID = legID;
    }

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.callInformationReport_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.callInformationReport;
    }

    @Override
    public ArrayList<RequestedInformation> getRequestedInformationList() {
        return requestedInformationList;
    }

    @Override
    public CAPExtensions getExtensions() {
        return extensions;
    }

    @Override
    public ReceivingSideID getLegID() {
        return legID;
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

        this.requestedInformationList = null;
        this.extensions = null;
        this.legID = null;
        // this.legID = new ReceivingSideIDImpl(LegType.leg2);

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_requestedInformationList:
                        this.requestedInformationList = new ArrayList<RequestedInformation>();
                        AsnInputStream ais2 = ais.readSequenceStream();
                        while (true) {
                            if (ais2.available() == 0)
                                break;

                            int tag2 = ais2.readTag();
                            if (tag2 != Tag.SEQUENCE || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || ais2.isTagPrimitive())
                                throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ": bad RequestedInformation tag or tagClass or RequestedInformation is primitive ",
                                        CAPParsingComponentExceptionReason.MistypedParameter);

                            RequestedInformationImpl el = new RequestedInformationImpl();
                            el.decodeAll(ais2);
                            this.requestedInformationList.add(el);
                        }
                        break;
                    case _ID_extensions:
                        this.extensions = new CAPExtensionsImpl();
                        ((CAPExtensionsImpl) this.extensions).decodeAll(ais);
                        break;
                    case _ID_legID:
                        ais2 = ais.readSequenceStream();
                        ais2.readTag();
                        this.legID = new ReceivingSideIDImpl();
                        ((ReceivingSideIDImpl) this.legID).decodeAll(ais2);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.requestedInformationList == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": requestedInformationList is mandatory but not found ",
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

        if (this.requestedInformationList == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": requestedInformationList must not be null");
        if (this.requestedInformationList.size() < 1 || this.requestedInformationList.size() > 4)
            throw new CAPException("Error while encoding " + _PrimitiveName
                    + ": requestedInformationList size must be from 1 to 4");

        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_requestedInformationList);
            int pos = asnOutputStream.StartContentDefiniteLength();
            for (RequestedInformation ri : this.requestedInformationList) {
                RequestedInformationImpl rii = (RequestedInformationImpl) ri;
                rii.encodeAll(asnOutputStream, rii.getTagClass(), rii.getTag());
            }
            asnOutputStream.FinalizeContent(pos);

            if (this.extensions != null)
                ((CAPExtensionsImpl) this.extensions).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_extensions);

            if (this.legID != null) {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_legID);
                pos = asnOutputStream.StartContentDefiniteLength();
                ((ReceivingSideIDImpl) this.legID).encodeAll(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            }

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

        if (this.requestedInformationList != null) {
            sb.append(", requestedInformationList=[");
            boolean firstItem = true;
            for (RequestedInformation ri : this.requestedInformationList) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append("requestedInformation=[");
                sb.append(ri.toString());
                sb.append("]");
            }
            sb.append("]");
        }
        if (this.extensions != null) {
            sb.append(", extensions=");
            sb.append(extensions.toString());
        }
        if (this.legID != null) {
            sb.append(", legID=");
            sb.append(legID.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
