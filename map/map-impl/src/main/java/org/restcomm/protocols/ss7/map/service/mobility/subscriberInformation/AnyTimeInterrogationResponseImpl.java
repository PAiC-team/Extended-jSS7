package org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPMessageType;
import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.AnyTimeInterrogationResponse;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.SubscriberInfo;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.mobility.MobilityMessageImpl;

/**
 * @author amit bhayani
 *
 */
public class AnyTimeInterrogationResponseImpl extends MobilityMessageImpl implements AnyTimeInterrogationResponse, MAPAsnPrimitive {

    public static final String _PrimitiveName = "AnyTimeInterrogationResponse";

    private static final String SUBSCRIBER_INFO = "subscriberInfo";
    private static final String EXTENSION_CONTAINER = "extensionContainer";

    private SubscriberInfo subscriberInfo;
    private MAPExtensionContainer extensionContainer;

    /**
     *
     */
    public AnyTimeInterrogationResponseImpl() {
    }

    /**
     * @param subscriberInfo
     * @param extensionContainer
     */
    public AnyTimeInterrogationResponseImpl(SubscriberInfo subscriberInfo, MAPExtensionContainer extensionContainer) {
        super();
        this.subscriberInfo = subscriberInfo;
        this.extensionContainer = extensionContainer;
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
     * @see
     * org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getTagClass()
     */
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#getIsPrimitive
     * ()
     */
    public boolean getIsPrimitive() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeAll(
     * org.mobicents.protocols.asn.AsnInputStream)
     */
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

    /*
     * (non-Javadoc)
     *
     * @see
     * org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#decodeData
     * (org.mobicents.protocols.asn.AsnInputStream, int)
     */
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

        // decode subscriberInfo
        int tag = ais.readTag();
        if (tag != Tag.SEQUENCE || ais.getTagClass() != Tag.CLASS_UNIVERSAL || ais.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter subscriberInfo has bad tag or tag class or not primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        this.subscriberInfo = new SubscriberInfoImpl();
        ((SubscriberInfoImpl) this.subscriberInfo).decodeAll(ais);

        while (true) {
            if (ais.available() == 0)
                break;

            tag = ais.readTag();

            // optional parameters
            if (ais.getTagClass() == Tag.CLASS_UNIVERSAL) {
                switch (tag) {
                case Tag.SEQUENCE:
                    if (ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ": Parameter extensionContainer is not primitive", MAPParsingComponentExceptionReason.MistypedParameter);
                    extensionContainer = new MAPExtensionContainerImpl();
                    ((MAPExtensionContainerImpl) extensionContainer).decodeAll(ais);
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

    /*
     * (non-Javadoc)
     *
     * @see
     * org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll(
     * org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeAll(
     * org.mobicents.protocols.asn.AsnOutputStream, int, int)
     */
    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, this.getIsPrimitive(), tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + " : " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeData
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.subscriberInfo == null)
            throw new MAPException("Error when encoding " + _PrimitiveName + ": SubscriberInfo cannot be null");

        ((SubscriberInfoImpl) this.subscriberInfo).encodeAll(asnOutputStream);

        if (this.extensionContainer != null)
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.
     * AnyTimeInterrogationResponseIndication#getSubscriberInfo()
     */
    public SubscriberInfo getSubscriberInfo() {
        return this.subscriberInfo;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.subscriberInformation.
     * AnyTimeInterrogationResponseIndication#getExtensionContainer()
     */
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.subscriberInfo != null) {
            sb.append("subscriberInfo=");
            sb.append(this.subscriberInfo);
        }
        if (this.extensionContainer != null) {
            sb.append(", extensionContainer=");
            sb.append(this.extensionContainer);
        }

        sb.append("]");
        return sb.toString();
    }

    protected static final XMLFormat<AnyTimeInterrogationResponseImpl> ANY_TIME_INTERROGATION_RESPONSE_XML = new XMLFormat<AnyTimeInterrogationResponseImpl>(
            AnyTimeInterrogationResponseImpl.class) {

        @Override
        public void write(AnyTimeInterrogationResponseImpl obj, javolution.xml.XMLFormat.OutputElement xml) throws XMLStreamException {
            xml.add((SubscriberInfoImpl) obj.subscriberInfo, SUBSCRIBER_INFO, SubscriberInfoImpl.class);
            if (obj.extensionContainer != null) {
                xml.add((MAPExtensionContainerImpl) obj.extensionContainer, EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
            }
        }

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, AnyTimeInterrogationResponseImpl obj) throws XMLStreamException {
            obj.subscriberInfo = xml.get(SUBSCRIBER_INFO, SubscriberInfoImpl.class);
            obj.extensionContainer = xml.get(EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

    };
}
