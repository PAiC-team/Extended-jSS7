
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
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageUnauthorizedLCSClient;
import org.restcomm.protocols.ss7.map.api.errors.UnauthorizedLCSClientDiagnostic;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageUnauthorizedLCSClientImpl extends MAPErrorMessageImpl implements MAPErrorMessageUnauthorizedLCSClient {

    private static final String UNAUTHORIZED_LCS_CLIENT_DIAGNOSTIC = "unauthorizedLCSClientDiagnostic";
    private static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";

    public static final int UnauthorizedLCSClientDiagnostic_TAG = 0;
    public static final int ExtensionContainer_TAG = 1;

    private UnauthorizedLCSClientDiagnostic unauthorizedLCSClientDiagnostic;
    private MAPExtensionContainer extensionContainer;

    public MAPErrorMessageUnauthorizedLCSClientImpl(UnauthorizedLCSClientDiagnostic unauthorizedLCSClientDiagnostic,
            MAPExtensionContainer extensionContainer) {
        super((long) MAPErrorCode.unauthorizedLCSClient);

        this.unauthorizedLCSClientDiagnostic = unauthorizedLCSClientDiagnostic;
        this.extensionContainer = extensionContainer;
    }

    public MAPErrorMessageUnauthorizedLCSClientImpl() {
        super((long) MAPErrorCode.unauthorizedLCSClient);
    }

    public boolean isEmUnauthorizedLCSClient() {
        return true;
    }

    public MAPErrorMessageUnauthorizedLCSClient getEmUnauthorizedLCSClient() {
        return this;
    }

    public UnauthorizedLCSClientDiagnostic getUnauthorizedLCSClientDiagnostic() {
        return this.unauthorizedLCSClientDiagnostic;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public void setUnauthorizedLCSClientDiagnostic(UnauthorizedLCSClientDiagnostic unauthorizedLCSClientDiagnostic) {
        this.unauthorizedLCSClientDiagnostic = unauthorizedLCSClientDiagnostic;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
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
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageUnauthorizedLCSClient: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageUnauthorizedLCSClient: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageUnauthorizedLCSClient: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageUnauthorizedLCSClient: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream localAsnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.extensionContainer = null;
        this.unauthorizedLCSClientDiagnostic = null;

        if (localAsnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL || localAsnInputStream.getTag() != Tag.SEQUENCE
            || localAsnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException(
                    "Error decoding MAPErrorMessageUnauthorizedLCSClient: bad tag class or tag or parameter is primitive",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        AsnInputStream ais = localAsnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (ais.getTagClass()) {
                case Tag.CLASS_CONTEXT_SPECIFIC:
                    switch (tag) {
                        case UnauthorizedLCSClientDiagnostic_TAG:
                            int code = (int) ais.readInteger();
                            this.unauthorizedLCSClientDiagnostic = UnauthorizedLCSClientDiagnostic.getInstance(code);
                            break;

                        case ExtensionContainer_TAG:
                            this.extensionContainer = new MAPExtensionContainerImpl();
                            ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
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
            throw new MAPException("AsnException when encoding MAPErrorMessageUnauthorizedLCSClient: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.unauthorizedLCSClientDiagnostic == null && this.extensionContainer == null)
            return;

        try {
            if (this.unauthorizedLCSClientDiagnostic != null)
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, UnauthorizedLCSClientDiagnostic_TAG,
                        this.unauthorizedLCSClientDiagnostic.getCode());
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                        ExtensionContainer_TAG);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPErrorMessageUnauthorizedLCSClient: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageUnauthorizedLCSClient: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MAPErrorMessageUnauthorizedLCSClient [");
        if (this.unauthorizedLCSClientDiagnostic != null)
            sb.append("unauthorizedLCSClientDiagnostic=" + this.unauthorizedLCSClientDiagnostic.toString());
        if (this.extensionContainer != null)
            sb.append(", extensionContainer=" + this.extensionContainer.toString());
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageUnauthorizedLCSClientImpl> MAP_ERROR_MESSAGE_UNAUTHORIZED_LCS_CLIENT_XML = new XMLFormat<MAPErrorMessageUnauthorizedLCSClientImpl>(
            MAPErrorMessageUnauthorizedLCSClientImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageUnauthorizedLCSClientImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
            String str = xml.get(UNAUTHORIZED_LCS_CLIENT_DIAGNOSTIC, String.class);
            if (str != null)
                errorMessage.unauthorizedLCSClientDiagnostic = Enum.valueOf(UnauthorizedLCSClientDiagnostic.class, str);

            errorMessage.extensionContainer = xml.get(MAP_EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MAPErrorMessageUnauthorizedLCSClientImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
            if (errorMessage.getUnauthorizedLCSClientDiagnostic() != null)
                xml.add((String) errorMessage.getUnauthorizedLCSClientDiagnostic().toString(),
                        UNAUTHORIZED_LCS_CLIENT_DIAGNOSTIC, String.class);

            xml.add((MAPExtensionContainerImpl) errorMessage.extensionContainer, MAP_EXTENSION_CONTAINER,
                    MAPExtensionContainerImpl.class);
        }
    };
}
