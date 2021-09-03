
package org.restcomm.protocols.ss7.map.service.supplementary;

import java.io.IOException;
import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingInfo;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
*
* @author sergey vetyutnev
*
*/
public class ForwardingInfoImpl extends SequenceBase implements ForwardingInfo {

    private SSCode ssCode;
    private ArrayList<ForwardingFeature> forwardingFeatureList;

    public ForwardingInfoImpl() {
        super("ForwardingInfo");
    }

    public ForwardingInfoImpl(SSCode ssCode, ArrayList<ForwardingFeature> forwardingFeatureList) {
        super("ForwardingInfo");
        this.ssCode = ssCode;
        this.forwardingFeatureList = forwardingFeatureList;
    }

    @Override
    public SSCode getSsCode() {
        return ssCode;
    }

    @Override
    public ArrayList<ForwardingFeature> getForwardingFeatureList() {
        return forwardingFeatureList;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.ssCode = null;
        this.forwardingFeatureList = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_UNIVERSAL) {

                switch (tag) {
                case Tag.STRING_OCTET:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " ssCode: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.ssCode = new SSCodeImpl();
                    ((SSCodeImpl) this.ssCode).decodeAll(ais);
                    break;

                case Tag.SEQUENCE:
                    if (ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + ".forwardingFeatureList: Parameter is primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);

                    AsnInputStream ais2 = ais.readSequenceStream();
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

                default:
                    ais.advanceElement();
                    break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (forwardingFeatureList == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": forwardingFeatureList parameter is mandatory but has not found", MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.forwardingFeatureList == null)
            throw new MAPException("forwardingFeatureList parameter must not be null");

        if (this.ssCode != null)
            ((SSCodeImpl) this.ssCode).encodeAll(asnOutputStream);

        try {
            asnOutputStream.writeTag(Tag.CLASS_UNIVERSAL, false, Tag.SEQUENCE);
            int pos = asnOutputStream.StartContentDefiniteLength();
            for (ForwardingFeature item : this.forwardingFeatureList) {
                ((ForwardingFeatureImpl) item).encodeAll(asnOutputStream);
            }
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ".forwardingFeatureList: " + e.getMessage(), e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.ssCode != null) {
            sb.append("ssCode=");
            sb.append(this.ssCode);
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

        sb.append("]");
        return sb.toString();
    }

}
