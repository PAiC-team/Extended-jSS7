package org.restcomm.protocols.ss7.map.errors;

import java.io.IOException;

import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorCode;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageSubscriberBusyForMtSms;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageSubscriberBusyForMtSmsImpl extends MAPErrorMessageImpl implements MAPErrorMessageSubscriberBusyForMtSms {

    private static final String GPRS_CONNECTION_SUSPENDED = "gprsConnectionSuspended";
    private static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";

    private MAPExtensionContainer extensionContainer;
    private Boolean gprsConnectionSuspended;

    public MAPErrorMessageSubscriberBusyForMtSmsImpl(MAPExtensionContainer extensionContainer, Boolean gprsConnectionSuspended) {
        super((long) MAPErrorCode.subscriberBusyForMTSMS);
        this.extensionContainer = extensionContainer;
        this.gprsConnectionSuspended = gprsConnectionSuspended;
    }

    public MAPErrorMessageSubscriberBusyForMtSmsImpl() {
        super((long) MAPErrorCode.subscriberBusyForMTSMS);
    }

    public boolean isEmSubscriberBusyForMtSms() {
        return true;
    }

    public MAPErrorMessageSubscriberBusyForMtSms getEmSubscriberBusyForMtSms() {
        return this;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public Boolean getGprsConnectionSuspended() {
        return this.gprsConnectionSuspended;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public void setGprsConnectionSuspended(Boolean gprsConnectionSuspended) {
        this.gprsConnectionSuspended = gprsConnectionSuspended;
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
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageSubscriberBusyForMtSms: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageSubscriberBusyForMtSms: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageSubscriberBusyForMtSms: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageSubscriberBusyForMtSms: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream localAsnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.extensionContainer = null;
        this.gprsConnectionSuspended = null;

        if (localAsnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL || localAsnInputStream.getTag() != Tag.SEQUENCE
            || localAsnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException(
                    "Error decoding MAPErrorMessageSubscriberBusyForMtSms: bad tag class or tag or parameter is primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        AsnInputStream ais = localAsnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (ais.getTagClass()) {
                case Tag.CLASS_UNIVERSAL:
                    switch (tag) {
                        case Tag.SEQUENCE:
                            this.extensionContainer = new MAPExtensionContainerImpl();
                            ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                            break;

                        case Tag.NULL:
                            ais.readNull();
                            this.gprsConnectionSuspended = true;
                            break;

                        default:
                            ais.advanceElement();
                            break;
                    }
                    break;

                default:
                    ais.advanceElement();
                    break;
            }
        }

        if (this.gprsConnectionSuspended == null)
            this.gprsConnectionSuspended = false;
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
            throw new MAPException("AsnException when encoding MAPErrorMessageSubscriberBusyForMtSms: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.gprsConnectionSuspended == null && this.extensionContainer == null)
            return;

        try {
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
            if (this.gprsConnectionSuspended != null && this.gprsConnectionSuspended == true)
                asnOutputStream.writeNull();

        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPErrorMessageSubscriberBusyForMtSms: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageSubscriberBusyForMtSms: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MAPErrorMessageSubscriberBusyForMtSms [");
        if (this.extensionContainer != null)
            sb.append("extensionContainer=" + this.extensionContainer.toString());
        if (this.gprsConnectionSuspended != null && this.gprsConnectionSuspended == true)
            sb.append(", gprsConnectionSuspended=true");
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageSubscriberBusyForMtSmsImpl> MAP_ERROR_MESSAGE_SUBSCRIBER_BUSY_FOR_MT_SMS_XML = new XMLFormat<MAPErrorMessageSubscriberBusyForMtSmsImpl>(
            MAPErrorMessageSubscriberBusyForMtSmsImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageSubscriberBusyForMtSmsImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
            errorMessage.gprsConnectionSuspended = xml.get(GPRS_CONNECTION_SUSPENDED, Boolean.class);
            errorMessage.extensionContainer = xml.get(MAP_EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MAPErrorMessageSubscriberBusyForMtSmsImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
            xml.add(errorMessage.getGprsConnectionSuspended(), GPRS_CONNECTION_SUSPENDED, Boolean.class);
            xml.add((MAPExtensionContainerImpl) errorMessage.extensionContainer, MAP_EXTENSION_CONTAINER,
                    MAPExtensionContainerImpl.class);
        }
    };
}
