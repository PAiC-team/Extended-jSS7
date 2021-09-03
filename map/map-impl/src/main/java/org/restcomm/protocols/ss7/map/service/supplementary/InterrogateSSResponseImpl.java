
package org.restcomm.protocols.ss7.map.service.supplementary;

import java.io.IOException;
import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.GenericServiceInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.InterrogateSSResponse;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class InterrogateSSResponseImpl extends SupplementaryMessageImpl implements InterrogateSSResponse {

    public static final int _TAG_ssStatus = 0;
    public static final int _TAG_basicServiceGroupList = 2;
    public static final int _TAG_forwardingFeatureList = 3;
    public static final int _TAG_genericServiceInfo = 4;

    public static final String _PrimitiveName = "InterrogateSSResponse";

    private SSStatus ssStatus;
    private ArrayList<BasicServiceCode> basicServiceGroupList;
    private ArrayList<ForwardingFeature> forwardingFeatureList;
    private GenericServiceInfo genericServiceInfo;

    public InterrogateSSResponseImpl() {
    }

    public InterrogateSSResponseImpl(SSStatus ssStatus) {
        this.ssStatus = ssStatus;
    }

    public InterrogateSSResponseImpl(ArrayList<BasicServiceCode> basicServiceGroupList, boolean doommyPar) {
        this.basicServiceGroupList = basicServiceGroupList;
    }

    public InterrogateSSResponseImpl(ArrayList<ForwardingFeature> forwardingFeatureList) {
        this.forwardingFeatureList = forwardingFeatureList;
    }

    public InterrogateSSResponseImpl(GenericServiceInfo genericServiceInfo) {
        this.genericServiceInfo = genericServiceInfo;
    }

    @Override
    public MAPMessageType getMessageType() {
        return MAPMessageType.interrogateSS_Response;
    }

    @Override
    public int getOperationCode() {
        return MAPOperationCode.interrogateSS;
    }

    @Override
    public SSStatus getSsStatus() {
        return ssStatus;
    }

    @Override
    public ArrayList<BasicServiceCode> getBasicServiceGroupList() {
        return basicServiceGroupList;
    }

    @Override
    public ArrayList<ForwardingFeature> getForwardingFeatureList() {
        return forwardingFeatureList;
    }

    @Override
    public GenericServiceInfo getGenericServiceInfo() {
        return genericServiceInfo;
    }


    @Override
    public int getTag() throws MAPException {
        if (ssStatus != null) {
            return _TAG_ssStatus;
        } else if (basicServiceGroupList != null) {
            return _TAG_basicServiceGroupList;
        } else if (forwardingFeatureList != null) {
            return _TAG_forwardingFeatureList;
        } else if (genericServiceInfo != null) {
            return _TAG_genericServiceInfo;
        } else {
            throw new MAPException("No of choices are supplied");
        }
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    @Override
    public boolean getIsPrimitive() {
        if (ssStatus != null)
            return true;
        else
            return false;
    }

    @Override
    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.ssStatus = null;
        this.basicServiceGroupList = null;
        this.forwardingFeatureList = null;
        this.genericServiceInfo = null;

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ": bad tag class: TagClass=" + asnInputStream.getTagClass(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
        case _TAG_ssStatus:
            this.ssStatus = new SSStatusImpl();
            ((SSStatusImpl) this.ssStatus).decodeData(asnInputStream, length);
            break;
        case _TAG_basicServiceGroupList:
            if (asnInputStream.isTagPrimitive())
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".basicServiceGroupList: Parameter is primitive",
                        MAPParsingComponentExceptionReason.MistypedParameter);

            AsnInputStream ais2 = asnInputStream.readSequenceStreamData(length);
            this.basicServiceGroupList = new ArrayList<BasicServiceCode>();
            while (true) {
                if (ais2.available() == 0)
                    break;

                ais2.readTag();

                BasicServiceCode basicServiceCode = new BasicServiceCodeImpl();
                ((BasicServiceCodeImpl) basicServiceCode).decodeAll(ais2);
                this.basicServiceGroupList.add(basicServiceCode);
            }
            if (this.basicServiceGroupList.size() < 1 || this.basicServiceGroupList.size() > 13) {
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                        + ": Parameter basicServiceGroupList size must be from 1 to 13, found: " + this.basicServiceGroupList.size(),
                        MAPParsingComponentExceptionReason.MistypedParameter);
            }
            break;
        case _TAG_forwardingFeatureList:
            if (asnInputStream.isTagPrimitive())
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".forwardingFeatureList: Parameter is primitive",
                        MAPParsingComponentExceptionReason.MistypedParameter);

            ais2 = asnInputStream.readSequenceStreamData(length);
            this.forwardingFeatureList = new ArrayList<ForwardingFeature>();
            while (true) {
                if (ais2.available() == 0)
                    break;

                ais2.readTag();

                ForwardingFeature forwardingFeature = new ForwardingFeatureImpl();
                ((ForwardingFeatureImpl) forwardingFeature).decodeAll(ais2);
                this.forwardingFeatureList.add(forwardingFeature);
            }
            if (this.forwardingFeatureList.size() < 1 || this.forwardingFeatureList.size() > 13) {
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                        + ": Parameter forwardingFeatureList size must be from 1 to 13, found: " + this.forwardingFeatureList.size(),
                        MAPParsingComponentExceptionReason.MistypedParameter);
            }
            break;
        case _TAG_genericServiceInfo:
            this.genericServiceInfo = new GenericServiceInfoImpl();
            ((GenericServiceInfoImpl) this.genericServiceInfo).decodeData(asnInputStream, length);
            break;

        default:
            throw new MAPParsingComponentException("Error while " + _PrimitiveName + ": bad tag: " + asnInputStream.getTag(),
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    @Override
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        int cnt = 0;
        if (ssStatus != null)
            cnt++;
        if (basicServiceGroupList != null)
            cnt++;
        if (forwardingFeatureList != null)
            cnt++;
        if (genericServiceInfo != null)
            cnt++;

        if (cnt == 0)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": no option is set.");
        if (cnt > 1)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": more than 1 option is set.");

        if (this.ssStatus != null)
            ((SSStatusImpl) this.ssStatus).encodeData(asnOutputStream);

        if (this.basicServiceGroupList != null) {
            for (BasicServiceCode item : this.basicServiceGroupList) {
                ((BasicServiceCodeImpl) item).encodeAll(asnOutputStream);
            }
        }

        if (this.forwardingFeatureList != null) {
            for (ForwardingFeature item : this.forwardingFeatureList) {
                ((ForwardingFeatureImpl) item).encodeAll(asnOutputStream);
            }
        }

        if (this.genericServiceInfo != null)
            ((GenericServiceInfoImpl) this.genericServiceInfo).encodeData(asnOutputStream);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.ssStatus != null) {
            sb.append("ssStatus=");
            sb.append(ssStatus);
            sb.append(", ");
        }
        if (this.basicServiceGroupList != null) {
            sb.append("basicServiceGroupList=[");
            boolean firstItem = true;
            for (BasicServiceCode be : this.basicServiceGroupList) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(be.toString());
            }
            sb.append("], ");
        }
        if (this.forwardingFeatureList != null) {
            sb.append("forwardingFeatureList=[");
            boolean firstItem = true;
            for (ForwardingFeature be : this.forwardingFeatureList) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(be.toString());
            }
            sb.append("], ");
        }
        if (this.genericServiceInfo != null) {
            sb.append("genericServiceInfo=");
            sb.append(genericServiceInfo);
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

}
