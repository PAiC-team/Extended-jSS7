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
import org.restcomm.protocols.ss7.cap.api.primitives.SendingSideID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.CallInformationRequestRequest;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.RequestedInformationType;
import org.restcomm.protocols.ss7.cap.primitives.CAPExtensionsImpl;
import org.restcomm.protocols.ss7.cap.primitives.SendingSideIDImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class CallInformationRequestRequestImpl extends CircuitSwitchedCallMessageImpl implements CallInformationRequestRequest {

    public static final int _ID_requestedInformationTypeList = 0;
    public static final int _ID_extensions = 2;
    public static final int _ID_legID = 3;

    public static final String _PrimitiveName = "CallInformationRequestRequestIndication";

    private ArrayList<RequestedInformationType> requestedInformationTypeList;
    private CAPExtensions extensions;
    private SendingSideID legID;

    public CallInformationRequestRequestImpl() {
    }

    public CallInformationRequestRequestImpl(ArrayList<RequestedInformationType> requestedInformationTypeList,
            CAPExtensions extensions, SendingSideID legID) {
        this.requestedInformationTypeList = requestedInformationTypeList;
        this.extensions = extensions;
        this.legID = legID;
    }

    @Override
    public CAPMessageType getMessageType() {
        return CAPMessageType.callInformationRequest_Request;
    }

    @Override
    public int getOperationCode() {
        return CAPOperationCode.callInformationRequest;
    }

    @Override
    public ArrayList<RequestedInformationType> getRequestedInformationTypeList() {
        return requestedInformationTypeList;
    }

    @Override
    public CAPExtensions getExtensions() {
        return extensions;
    }

    @Override
    public SendingSideID getLegID() {
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

        this.requestedInformationTypeList = null;
        this.extensions = null;
        this.legID = null;
        // this.legID = new SendingSideIDImpl(LegType.leg2);

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_requestedInformationTypeList:
                        this.requestedInformationTypeList = new ArrayList<RequestedInformationType>();
                        AsnInputStream ais2 = ais.readSequenceStream();
                        while (true) {
                            if (ais2.available() == 0)
                                break;

                            int tag2 = ais2.readTag();

                            if (tag2 != Tag.ENUMERATED || ais2.getTagClass() != Tag.CLASS_UNIVERSAL || !ais2.isTagPrimitive())
                                throw new CAPParsingComponentException(
                                        "Error while decoding "
                                                + _PrimitiveName
                                                + ": bad RequestedInformationType tag or tagClass or RequestedInformationType is not primitive",
                                        CAPParsingComponentExceptionReason.MistypedParameter);

                            int i1 = (int) ais2.readInteger();
                            RequestedInformationType el = RequestedInformationType.getInstance(i1);
                            if (el == null)
                                throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ": bad RequestedInformationType value",
                                        CAPParsingComponentExceptionReason.MistypedParameter);
                            this.requestedInformationTypeList.add(el);
                        }
                        break;
                    case _ID_extensions:
                        this.extensions = new CAPExtensionsImpl();
                        ((CAPExtensionsImpl) this.extensions).decodeAll(ais);
                        break;
                    case _ID_legID:
                        ais2 = ais.readSequenceStream();
                        ais2.readTag();
                        this.legID = new SendingSideIDImpl();
                        ((SendingSideIDImpl) this.legID).decodeAll(ais2);
                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.requestedInformationTypeList == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter requestedInformationTypeList is mandatory parameters, but not found",
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

        if (this.requestedInformationTypeList == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": requestedInformationTypeList must not be null");
        if (this.requestedInformationTypeList.size() < 1 || this.requestedInformationTypeList.size() > 4)
            throw new CAPException("Error while encoding " + _PrimitiveName
                    + ": requestedInformationTypeList size must be from 1 to 4");

        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_requestedInformationTypeList);
            int pos = asnOutputStream.StartContentDefiniteLength();
            for (RequestedInformationType ri : this.requestedInformationTypeList) {
                asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, ri.getCode());
            }
            asnOutputStream.FinalizeContent(pos);

            if (this.extensions != null)
                ((CAPExtensionsImpl) this.extensions).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_extensions);

            if (this.legID != null) {
                asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_legID);
                pos = asnOutputStream.StartContentDefiniteLength();
                ((SendingSideIDImpl) this.legID).encodeAll(asnOutputStream);
                asnOutputStream.FinalizeContent(pos);
            }

        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (IOException e) {
            throw new CAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");
        this.addInvokeIdInfo(sb);

        if (this.requestedInformationTypeList != null) {
            sb.append(", requestedInformationTypeList=[");
            boolean firstItem = true;
            for (RequestedInformationType ri : this.requestedInformationTypeList) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(ri.toString());
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
