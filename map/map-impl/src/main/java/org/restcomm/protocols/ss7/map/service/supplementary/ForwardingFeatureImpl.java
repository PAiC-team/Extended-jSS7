
package org.restcomm.protocols.ss7.map.service.supplementary;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.FTNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingFeature;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingOptions;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;
import org.restcomm.protocols.ss7.map.primitives.FTNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.BasicServiceCodeImpl;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class ForwardingFeatureImpl extends SequenceBase implements ForwardingFeature {

    public static final int _ID_ssStatus = 4;
    public static final int _ID_forwardedToNumber = 5;
    public static final int _ID_forwardedToSubaddress = 8;
    public static final int _ID_forwardingOptions = 6;
    public static final int _ID_noReplyConditionTime = 7;
    public static final int _ID_longForwardedToNumber = 9;

    private BasicServiceCode basicService;
    private SSStatus ssStatus;
    private ISDNAddressString torwardedToNumber;
    private ISDNAddressString forwardedToSubaddress;
    private ForwardingOptions forwardingOptions;
    private Integer noReplyConditionTime;
    private FTNAddressString longForwardedToNumber;

    public ForwardingFeatureImpl() {
        super("ForwardingFeature");
    }

    public ForwardingFeatureImpl(BasicServiceCode basicService, SSStatus ssStatus, ISDNAddressString torwardedToNumber,
            ISDNAddressString forwardedToSubaddress, ForwardingOptions forwardingOptions, Integer noReplyConditionTime, FTNAddressString longForwardedToNumber) {
        super("ForwardingFeature");

        this.basicService = basicService;
        this.ssStatus = ssStatus;
        this.torwardedToNumber = torwardedToNumber;
        this.forwardedToSubaddress = forwardedToSubaddress;
        this.forwardingOptions = forwardingOptions;
        this.noReplyConditionTime = noReplyConditionTime;
        this.longForwardedToNumber = longForwardedToNumber;
    }

    @Override
    public BasicServiceCode getBasicService() {
        return basicService;
    }

    @Override
    public SSStatus getSsStatus() {
        return ssStatus;
    }

    @Override
    public ISDNAddressString getForwardedToNumber() {
        return torwardedToNumber;
    }

    @Override
    public ISDNAddressString getForwardedToSubaddress() {
        return forwardedToSubaddress;
    }

    @Override
    public ForwardingOptions getForwardingOptions() {
        return forwardingOptions;
    }

    @Override
    public Integer getNoReplyConditionTime() {
        return noReplyConditionTime;
    }

    @Override
    public FTNAddressString getLongForwardedToNumber() {
        return longForwardedToNumber;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.basicService = null;
        this.ssStatus = null;
        this.torwardedToNumber = null;
        this.forwardedToSubaddress = null;
        this.forwardingOptions = null;
        this.noReplyConditionTime = null;
        this.longForwardedToNumber = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {

                switch (tag) {
                case BasicServiceCodeImpl._TAG_bearerService:
                case BasicServiceCodeImpl._TAG_teleservice:
                    this.basicService = new BasicServiceCodeImpl();
                    ((BasicServiceCodeImpl) this.basicService).decodeAll(ais);
                    break;

                case _ID_ssStatus:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " ssStatus: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.ssStatus = new SSStatusImpl();
                    ((SSStatusImpl) this.ssStatus).decodeAll(ais);
                    break;
                case _ID_forwardedToNumber:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " torwardedToNumber: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.torwardedToNumber = new ISDNAddressStringImpl();
                    ((ISDNAddressStringImpl) this.torwardedToNumber).decodeAll(ais);
                    break;
                case _ID_forwardedToSubaddress:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " forwardedToSubaddress: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.forwardedToSubaddress = new ISDNAddressStringImpl();
                    ((ISDNAddressStringImpl) this.forwardedToSubaddress).decodeAll(ais);
                    break;
                case _ID_forwardingOptions:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " forwardingOptions: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.forwardingOptions = new ForwardingOptionsImpl();
                    ((ForwardingOptionsImpl) this.forwardingOptions).decodeAll(ais);
                    break;
                case _ID_noReplyConditionTime:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " noReplyConditionTime: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.noReplyConditionTime = (int) ais.readInteger();
                    break;
                case _ID_longForwardedToNumber:
                    if (!ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName + " longForwardedToNumber: Parameter is not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.longForwardedToNumber = new FTNAddressStringImpl();
                    ((FTNAddressStringImpl) this.longForwardedToNumber).decodeAll(ais);
                    break;
                default:
                    ais.advanceElement();
                    break;
                }
            } else {
                ais.advanceElement();
            }
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (this.basicService != null)
                ((BasicServiceCodeImpl) this.basicService).encodeAll(asnOutputStream);

            if (this.ssStatus != null)
                ((SSStatusImpl) this.ssStatus).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_ssStatus);
            if (this.torwardedToNumber != null)
                ((ISDNAddressStringImpl) this.torwardedToNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_forwardedToNumber);
            if (this.forwardedToSubaddress != null)
                ((ISDNAddressStringImpl) this.forwardedToSubaddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_forwardedToSubaddress);
            if (this.forwardingOptions != null)
                ((ForwardingOptionsImpl) this.forwardingOptions).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_forwardingOptions);
            if (this.noReplyConditionTime != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _ID_noReplyConditionTime, noReplyConditionTime);
            if (this.longForwardedToNumber != null)
                ((FTNAddressStringImpl) this.longForwardedToNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_longForwardedToNumber);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.basicService != null) {
            sb.append("basicService=");
            sb.append(this.basicService);
        }

        if (this.ssStatus != null) {
            sb.append(", ssStatus=");
            sb.append(this.ssStatus);
        }

        if (this.torwardedToNumber != null) {
            sb.append(", torwardedToNumber=");
            sb.append(this.torwardedToNumber);
        }

        if (this.forwardedToSubaddress != null) {
            sb.append(", forwardedToSubaddress=");
            sb.append(this.forwardedToSubaddress);
        }

        if (this.forwardingOptions != null) {
            sb.append(", forwardingOptions=");
            sb.append(this.forwardingOptions);
        }

        if (this.noReplyConditionTime != null) {
            sb.append(", noReplyConditionTime=");
            sb.append(this.noReplyConditionTime);
        }

        if (this.longForwardedToNumber != null) {
            sb.append(", longForwardedToNumber=");
            sb.append(this.longForwardedToNumber);
        }

        sb.append("]");
        return sb.toString();
    }

}
