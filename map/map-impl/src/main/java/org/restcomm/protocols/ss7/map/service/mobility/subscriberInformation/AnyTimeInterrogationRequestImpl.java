package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import java.io.IOException;

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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationRequest;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.RequestedInfo;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SubscriberIdentityImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
 * @author amit bhayani
 *
 */
public class AnyTimeInterrogationRequestImpl extends MobilityMessageImpl implements AnyTimeInterrogationRequest,
        MAPAsnPrimitive {

    private static final int _TAG_SUBSCRIBER_IDENTITY = 0;
    private static final int _TAG_REQUESTED_INFO = 1;
    private static final int _TAG_EXTENSION_CONTAINER = 2;
    private static final int _TAG_GSM_SCF_ADDRESS = 3;

    public static final String _PrimitiveName = "AnyTimeInterrogationRequest";

    private SubscriberIdentity subscriberIdentity;
    private RequestedInfo requestedInfo;
    private ISDNAddressString gsmSCFAddress;
    private MAPExtensionContainer extensionContainer;

    public AnyTimeInterrogationRequestImpl() {

    }

    public AnyTimeInterrogationRequestImpl(SubscriberIdentity subscriberIdentity, RequestedInfo requestedInfo,
            ISDNAddressString gsmSCFAddress, MAPExtensionContainer extensionContainer) {
        this.subscriberIdentity = subscriberIdentity;
        this.requestedInfo = requestedInfo;
        this.gsmSCFAddress = gsmSCFAddress;
        this.extensionContainer = extensionContainer;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTagClass()
     */
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getIsPrimitive ()
     */
    public boolean getIsPrimitive() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeAll( org.mobicents.protocols.asn.AsnInputStream)
     */
    public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {
        try {
            int length = ansIS.readLength();
            this._decode(ansIS, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeData (org.mobicents.protocols.asn.AsnInputStream,
     * int)
     */
    public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {
        try {
            this._decode(ansIS, length);
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
        this.requestedInfo = null;
        this.gsmSCFAddress = null;
        this.extensionContainer = null;

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
                    case _TAG_REQUESTED_INFO:
                        // decode RequestedInfo
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter requestedInfo is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.requestedInfo = new RequestedInfoImpl();
                        ((RequestedInfoImpl) this.requestedInfo).decodeAll(ais);
                        break;
                    case _TAG_EXTENSION_CONTAINER:
                        // decode extensionContainer
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ": Parameter extensionContainer is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) extensionContainer).decodeAll(ais);
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
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.subscriberIdentity == null || this.requestedInfo == null || this.gsmSCFAddress == null)
            throw new MAPParsingComponentException(
                    "Error while decoding "
                            + _PrimitiveName
                            + ": subscriberIdentity, requestedInfo and gsmSCFAddress parameters are mandatory but some of them are not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll( org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll( org.mobicents.protocols.asn.AsnOutputStream,
     * int, int)
     */
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeData (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.subscriberIdentity == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter subscriberIdentity is not defined");
        }
        if (this.requestedInfo == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter requestedInfo is not defined");
        }
        if (this.gsmSCFAddress == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter gsmSCF-Address is not defined");
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

        ((RequestedInfoImpl) this.requestedInfo).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_REQUESTED_INFO);

        ((ISDNAddressStringImpl) this.gsmSCFAddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_GSM_SCF_ADDRESS);

        if (this.extensionContainer != null) {
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _TAG_EXTENSION_CONTAINER);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.
     * AnyTimeInterrogationRequestIndication#getSubscriberIdentity()
     */
    public SubscriberIdentity getSubscriberIdentity() {
        return this.subscriberIdentity;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.
     * AnyTimeInterrogationRequestIndication#getRequestedInfo()
     */
    public RequestedInfo getRequestedInfo() {
        return this.requestedInfo;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.
     * AnyTimeInterrogationRequestIndication#getGsmSCFAddress()
     */
    public ISDNAddressString getGsmSCFAddress() {
        return this.gsmSCFAddress;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.
     * AnyTimeInterrogationRequestIndication#getExtensionContainer()
     */
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.MAPMessage#getMessageType()
     */
    public MAPMessageType getMessageType() {
        return MAPMessageType.anyTimeInterrogation_Request;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.MAPMessage#getOperationCode()
     */
    public int getOperationCode() {
        return MAPOperationCode.anyTimeInterrogation;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.subscriberIdentity != null) {
            sb.append("subscriberIdentity=");
            sb.append(this.subscriberIdentity);
        }
        if (this.requestedInfo != null) {
            sb.append(", requestedInfo=");
            sb.append(this.requestedInfo);
        }
        if (this.gsmSCFAddress != null) {
            sb.append(", gsmSCFAddress=");
            sb.append(this.gsmSCFAddress);
        }
        if (this.extensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.extensionContainer);
        }

        sb.append("]");
        return sb.toString();
    }
}
