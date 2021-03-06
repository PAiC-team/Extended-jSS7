
package org.restcomm.protocols.ss7.map.service.sms;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.sms.IpSmGwGuidance;
import org.restcomm.protocols.ss7.map.api.service.sms.LocationInfoWithLMSI;
import org.restcomm.protocols.ss7.map.api.service.sms.SendRoutingInfoForSMResponse;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

import java.io.IOException;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SendRoutingInfoForSMResponseImpl extends SmsMessageImpl implements SendRoutingInfoForSMResponse {

    protected static final int _TAG_LocationInfoWithLMSI = 0;
    protected static final int _TAG_mwdSet = 2;
    protected static final int _TAG_ExtensionContainer = 4;
    protected static final int _TAG_IpSmGwGuidance = 5;

    protected String _PrimitiveName = "SendRoutingInfoForSMResponse";

    private IMSI imsi;
    private LocationInfoWithLMSI locationInfoWithLMSI;
    private MAPExtensionContainer extensionContainer;
    private Boolean mwdSet;
    private IpSmGwGuidance ipSmGwGuidance;

    public SendRoutingInfoForSMResponseImpl() {
    }

    public SendRoutingInfoForSMResponseImpl(IMSI imsi, LocationInfoWithLMSI locationInfoWithLMSI,
            MAPExtensionContainer extensionContainer, Boolean mwdSet, IpSmGwGuidance ipSmGwGuidance) {
        this.imsi = imsi;
        this.locationInfoWithLMSI = locationInfoWithLMSI;
        this.extensionContainer = extensionContainer;
        this.mwdSet = mwdSet;
        this.ipSmGwGuidance = ipSmGwGuidance;
    }

    public MAPMessageType getMessageType() {
        return MAPMessageType.sendRoutingInfoForSM_Response;
    }

    public int getOperationCode() {
        return MAPOperationCode.sendRoutingInfoForSM;
    }

    public IMSI getIMSI() {
        return this.imsi;
    }

    public LocationInfoWithLMSI getLocationInfoWithLMSI() {
        return this.locationInfoWithLMSI;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public Boolean getMwdSet() {
        return mwdSet;
    }

    public IpSmGwGuidance getIpSmGwGuidance() {
        return ipSmGwGuidance;
    }

    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        return false;
    }

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
        this.imsi = null;
        this.locationInfoWithLMSI = null;
        this.extensionContainer = null;
        this.mwdSet = null;
        this.ipSmGwGuidance = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            switch (num) {
                case 0:
                    // imsi
                    if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || !ais.isTagPrimitive() || tag != Tag.STRING_OCTET)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".imsi: Parameter 0 bad tag or tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.imsi = new IMSIImpl();
                    ((IMSIImpl) this.imsi).decodeAll(ais);
                    break;

                case 1:
                    // locationInfoWithLMSI
                    if (ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || ais.isTagPrimitive()
                            || tag != _TAG_LocationInfoWithLMSI)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".locationInfoWithLMSI: Parameter 1 bad tag class or tag or primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.locationInfoWithLMSI = new LocationInfoWithLMSIImpl();
                    ((LocationInfoWithLMSIImpl) this.locationInfoWithLMSI).decodeAll(ais);
                    break;

                default:
                    if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                        switch (tag) {
                            case _TAG_ExtensionContainer:
                                if (ais.isTagPrimitive())
                                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                            + ".extensionContainer: Parameter extensionContainer is primitive",
                                            MAPParsingComponentExceptionReason.MistypedParameter);
                                this.extensionContainer = new MAPExtensionContainerImpl();
                                ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                                break;
                            case _TAG_mwdSet:
                                if (!ais.isTagPrimitive())
                                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                            + ".mwdSet: Parameter mwdSet is not primitive",
                                            MAPParsingComponentExceptionReason.MistypedParameter);
                                this.mwdSet = ais.readBoolean();
                                break;

                            case _TAG_IpSmGwGuidance:
                                if (ais.isTagPrimitive())
                                    throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                            + ".ipSmGwGuidance: Parameter ipSmGwGuidance is primitive",
                                            MAPParsingComponentExceptionReason.MistypedParameter);
                                this.ipSmGwGuidance = new IpSmGwGuidanceImpl();
                                ((IpSmGwGuidanceImpl) this.ipSmGwGuidance).decodeAll(ais);
                                break;
                            default:
                                ais.advanceElement();
                                break;
                        }

                    } else {

                        ais.advanceElement();
                    }
                    break;
            }

            num++;
        }

        if (num < 2)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Needs at least 2 mandatory parameters, found " + num,
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, false, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.imsi == null || this.locationInfoWithLMSI == null)
            throw new MAPException("imsi and locationInfoWithLMSI must not be null");

        ((IMSIImpl) this.imsi).encodeAll(asnOutputStream);
        ((LocationInfoWithLMSIImpl) this.locationInfoWithLMSI).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                _TAG_LocationInfoWithLMSI);
        if (this.extensionContainer != null)
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _TAG_ExtensionContainer);
        if (this.mwdSet != null) {
            try {
                asnOutputStream.writeBoolean(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_mwdSet, this.mwdSet);
            } catch (IOException e) {
                throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
            } catch (AsnException e) {
                throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
            }
        }
        if (this.ipSmGwGuidance != null) {
            ((IpSmGwGuidanceImpl) this.ipSmGwGuidance).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _TAG_IpSmGwGuidance);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendRoutingInfoForSMResponse [");

        if (this.getMAPDialog() != null) {
            sb.append("DialogId=").append(this.getMAPDialog().getLocalDialogId());
        }

        if (this.imsi != null) {
            sb.append(", imsi=");
            sb.append(this.imsi.toString());
        }
        if (this.locationInfoWithLMSI != null) {
            sb.append(", locationInfoWithLMSI=");
            sb.append(this.locationInfoWithLMSI.toString());
        }
        if (this.extensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.extensionContainer.toString());
        }
        if (this.mwdSet != null) {
            sb.append(", mwdSet=");
            sb.append(this.mwdSet.toString());
        }
        if (this.ipSmGwGuidance != null) {
            sb.append(", ipSmGwGuidance=");
            sb.append(this.ipSmGwGuidance.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
