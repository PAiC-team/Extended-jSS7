
package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.SubscriberIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeSubscriptionInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedSubscriptionInfo;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SubscriberIdentityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

import java.io.IOException;

/**
 * Created by vsubbotin on 24/05/16.
 */
public class AnyTimeSubscriptionInterrogationRequestImpl extends MobilityMessageImpl implements AnyTimeSubscriptionInterrogationRequest, MAPAsnPrimitive {

    private static final int _TAG_SUBSCRIBER_IDENTITY = 0;
    private static final int _TAG_REQUESTED_SUBSCRIPTION_INFO = 1;
    private static final int _TAG_GSM_SCF_ADDRESS = 2;
    private static final int _TAG_EXTENSION_CONTAINER = 3;
    private static final int _TAG_LONG_FTN_SUPPORTED = 4;

    public static final String _PrimitiveName = "AnyTimeSubscriptionInterrogationRequest";

    private SubscriberIdentity subscriberIdentity;
    private RequestedSubscriptionInfo requestedSubscriptionInfo;
    private ISDNAddressString gsmSCFAddress;
    private MAPExtensionContainer mapExtensionContainer;
    private boolean isLongFTNSupported;

    public AnyTimeSubscriptionInterrogationRequestImpl() {

    }

    public AnyTimeSubscriptionInterrogationRequestImpl(SubscriberIdentity subscriberIdentity, RequestedSubscriptionInfo requestedSubscriptionInfo,
            ISDNAddressString gsmSCFAddress, MAPExtensionContainer mapExtensionContainer, boolean isLongFTNSupported) {
        this.subscriberIdentity = subscriberIdentity;
        this.requestedSubscriptionInfo = requestedSubscriptionInfo;
        this.gsmSCFAddress = gsmSCFAddress;
        this.mapExtensionContainer = mapExtensionContainer;
        this.isLongFTNSupported = isLongFTNSupported;
    }

    public SubscriberIdentity getSubscriberIdentity() {
        return this.subscriberIdentity;
    }

    public RequestedSubscriptionInfo getRequestedSubscriptionInfo() {
        return this.requestedSubscriptionInfo;
    }

    public ISDNAddressString getGsmScfAddress() {
        return this.gsmSCFAddress;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.mapExtensionContainer;
    }

    public boolean getLongFTNSupported() {
        return this.isLongFTNSupported;
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
        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        this.subscriberIdentity = null;
        this.requestedSubscriptionInfo = null;
        this.gsmSCFAddress = null;
        this.mapExtensionContainer = null;
        this.isLongFTNSupported = false;

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _TAG_SUBSCRIBER_IDENTITY:
                        // decode SubscriberIdentity
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter subscriberIdentity is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.subscriberIdentity = new SubscriberIdentityImpl();
                        AsnInputStream ais2 = ais.readSequenceStream();
                        ais2.readTag();
                        ((SubscriberIdentityImpl) this.subscriberIdentity).decodeAll(ais2);
                        break;
                    case _TAG_REQUESTED_SUBSCRIPTION_INFO:
                        // decode RequestedSubscriptionInfo
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter requestedInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.requestedSubscriptionInfo = new RequestedSubscriptionInfoImpl();
                        ((RequestedSubscriptionInfoImpl)this.requestedSubscriptionInfo).decodeAll(ais);
                        break;
                    case _TAG_GSM_SCF_ADDRESS:
                        // decode gsmSCF-Address
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter gsmSCFAddress is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.gsmSCFAddress = new ISDNAddressStringImpl();
                        ((ISDNAddressStringImpl) this.gsmSCFAddress).decodeAll(ais);
                        break;
                    case _TAG_EXTENSION_CONTAINER:
                        // decode extensionContainer
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter extensionContainer is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        mapExtensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) mapExtensionContainer).decodeAll(ais);
                        break;
                    case _TAG_LONG_FTN_SUPPORTED:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter longFTNSupported is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        ais.readNull();
                        this.isLongFTNSupported = Boolean.TRUE;
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.subscriberIdentity == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": subscriberIdentity parameter is mandatory but is not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        if (this.requestedSubscriptionInfo == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": requestedSubscriptionInfo parameter is mandatory but is not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        if (this.gsmSCFAddress == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": gsmSCFAddress parameter is mandatory but is not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

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

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.subscriberIdentity == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter subscriberIdentity is not defined");
        }
        if (this.requestedSubscriptionInfo == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter requestedSubscriptionInfo is not defined");
        }
        if (this.gsmSCFAddress == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter gsmSCFAddress is not defined");
        }

        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG_SUBSCRIBER_IDENTITY);
            int pos = asnOutputStream.StartContentDefiniteLength();
            ((SubscriberIdentityImpl) this.subscriberIdentity).encodeAll(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException while encoding " + _PrimitiveName
                    + " parameter subscriberIdentity [0] SubscriberIdentity");
        }

        ((RequestedSubscriptionInfoImpl)this.requestedSubscriptionInfo).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_REQUESTED_SUBSCRIPTION_INFO);
        ((ISDNAddressStringImpl)this.gsmSCFAddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_GSM_SCF_ADDRESS);

        if (this.mapExtensionContainer != null) {
            ((MAPExtensionContainerImpl) this.mapExtensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_EXTENSION_CONTAINER);
        }

        if (this.isLongFTNSupported) {
            try {
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_LONG_FTN_SUPPORTED);
            } catch (IOException e) {
                throw new MAPException("IOException when encoding parameter longFTNSupported: ", e);
            } catch (AsnException e) {
                throw new MAPException("AsnException when encoding parameter longFTNSupported: ", e);
            }
        }
    }

    public MAPMessageType getMessageType() {
        return MAPMessageType.anyTimeSubscriptionInterrogation_Request;
    }

    public int getOperationCode() {
        return MAPOperationCode.anyTimeSubscriptionInterrogation;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.subscriberIdentity != null) {
            sb.append("subscriberIdentity=");
            sb.append(this.subscriberIdentity);
        }
        if (this.requestedSubscriptionInfo != null) {
            sb.append(", requestedSubscriptionInfo=");
            sb.append(this.requestedSubscriptionInfo);
        }
        if (this.gsmSCFAddress != null) {
            sb.append(", gsmSCFAddress=");
            sb.append(this.gsmSCFAddress);
        }
        if (this.mapExtensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.mapExtensionContainer);
        }

        if (this.isLongFTNSupported) {
            sb.append(", isLongFTNSupported");
        }

        sb.append("]");
        return sb.toString();
    }
}
