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
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageUnknownSubscriber;
import org.restcomm.protocols.ss7.map.api.errors.UnknownSubscriberDiagnostic;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageUnknownSubscriberImpl extends MAPErrorMessageImpl implements MAPErrorMessageUnknownSubscriber {

    private static final String UNKNOWN_SUBSCRIBER_DIAGNOSTIC = "unknownSubscriberDiagnostic";
    private static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";

    private MAPExtensionContainer extensionContainer;
    private UnknownSubscriberDiagnostic unknownSubscriberDiagnostic;

    public MAPErrorMessageUnknownSubscriberImpl(MAPExtensionContainer extensionContainer, UnknownSubscriberDiagnostic unknownSubscriberDiagnostic) {
        super((long) MAPErrorCode.unknownSubscriber);
        this.extensionContainer = extensionContainer;
        this.unknownSubscriberDiagnostic = unknownSubscriberDiagnostic;
    }

    public MAPErrorMessageUnknownSubscriberImpl() {
        super((long) MAPErrorCode.unknownSubscriber);
    }

    public boolean isEmUnknownSubscriber() {
        return true;
    }

    public MAPErrorMessageUnknownSubscriber getEmUnknownSubscriber() {
        return this;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public UnknownSubscriberDiagnostic getUnknownSubscriberDiagnostic() {
        return this.unknownSubscriberDiagnostic;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public void setUnknownSubscriberDiagnostic(UnknownSubscriberDiagnostic unknownSubscriberDiagnostic) {
        this.unknownSubscriberDiagnostic = unknownSubscriberDiagnostic;
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
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageUnknownSubscriberImpl: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageUnknownSubscriberImpl: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageUnknownSubscriberImpl: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageUnknownSubscriberImpl: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream localAsnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.unknownSubscriberDiagnostic = null;
        this.extensionContainer = null;

        if (localAsnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL || localAsnInputStream.getTag() != Tag.SEQUENCE
            || localAsnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException(
                    "Error decoding MAPErrorMessageUnknownSubscriberImpl: bad tag class or tag or parameter is primitive",
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

                        case Tag.ENUMERATED:
                            int code = (int) ais.readInteger();
                            this.unknownSubscriberDiagnostic = UnknownSubscriberDiagnostic.getInstance(code);
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
            throw new MAPException("AsnException when encoding MAPErrorMessageUnknownSubscriber: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.unknownSubscriberDiagnostic == null && this.extensionContainer == null)
            return;

        try {
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
            if (this.unknownSubscriberDiagnostic != null)
                asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.unknownSubscriberDiagnostic.getCode());

        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPErrorMessageUnknownSubscriber: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageUnknownSubscriber: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MAPErrorMessageUnknownSubscriber [");
        if (this.extensionContainer != null)
            sb.append("extensionContainer=" + this.extensionContainer.toString());
        if (this.unknownSubscriberDiagnostic != null)
            sb.append(", unknownSubscriberDiagnostic=" + this.unknownSubscriberDiagnostic.toString());
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageUnknownSubscriberImpl> MAP_ERROR_MESSAGE_UNKNOWN_SUBSCRIBER_XML = new XMLFormat<MAPErrorMessageUnknownSubscriberImpl>(
            MAPErrorMessageUnknownSubscriberImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageUnknownSubscriberImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
            String str = xml.get(UNKNOWN_SUBSCRIBER_DIAGNOSTIC, String.class);
            if (str != null)
                errorMessage.unknownSubscriberDiagnostic = Enum.valueOf(UnknownSubscriberDiagnostic.class, str);

            errorMessage.extensionContainer = xml.get(MAP_EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MAPErrorMessageUnknownSubscriberImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
            if (errorMessage.getUnknownSubscriberDiagnostic() != null)
                xml.add((String) errorMessage.getUnknownSubscriberDiagnostic().toString(), UNKNOWN_SUBSCRIBER_DIAGNOSTIC,
                        String.class);

            xml.add((MAPExtensionContainerImpl) errorMessage.extensionContainer, MAP_EXTENSION_CONTAINER,
                    MAPExtensionContainerImpl.class);
        }
    };
}
