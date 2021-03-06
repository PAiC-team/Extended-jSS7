
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
import org.restcomm.protocols.ss7.map.api.errors.CUGRejectCause;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorCode;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageCUGReject;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageCUGRejectImpl extends MAPErrorMessageImpl implements MAPErrorMessageCUGReject {

    private static final String CUG_REJECT_CAUSE = "cugRejectCause";
    private static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";

    private CUGRejectCause cugRejectCause;
    private MAPExtensionContainer extensionContainer;

    protected String _PrimitiveName = "MAPErrorMessageCUGReject";

    public MAPErrorMessageCUGRejectImpl(CUGRejectCause cugRejectCause, MAPExtensionContainer extensionContainer) {
        super((long) MAPErrorCode.cugReject);

        this.cugRejectCause = cugRejectCause;
        this.extensionContainer = extensionContainer;
    }

    public MAPErrorMessageCUGRejectImpl() {
        super((long) MAPErrorCode.cugReject);
    }

    public boolean isEmCUGReject() {
        return true;
    }

    public MAPErrorMessageCUGReject getEmCUGReject() {
        return this;
    }

    @Override
    public CUGRejectCause getCUGRejectCause() {
        return cugRejectCause;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    @Override
    public void setCUGRejectCause(CUGRejectCause val) {
        cugRejectCause = val;
    }

    @Override
    public void setExtensionContainer(MAPExtensionContainer val) {
        extensionContainer = val;
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

    private void _decode(AsnInputStream localAsnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.cugRejectCause = null;
        this.extensionContainer = null;

        if (localAsnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL || localAsnInputStream.getTag() != Tag.SEQUENCE
            || localAsnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error decoding " + _PrimitiveName
                    + ": bad tag class or tag or parameter is primitive", MAPParsingComponentExceptionReason.MistypedParameter);

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
                            int i1 = (int) ais.readInteger();
                            this.cugRejectCause = CUGRejectCause.getInstance(i1);
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
        if (this.cugRejectCause == null && this.extensionContainer == null)
            return;

        try {
            if (this.cugRejectCause != null)
                asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.cugRejectCause.getCode());
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.cugRejectCause != null)
            sb.append("cugRejectCause=" + this.cugRejectCause.toString());
        if (this.extensionContainer != null)
            sb.append(", extensionContainer=" + this.extensionContainer.toString());
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageCUGRejectImpl> MAP_ERROR_MESSAGE_CALL_BARRED_XML = new XMLFormat<MAPErrorMessageCUGRejectImpl>(
            MAPErrorMessageCUGRejectImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageCUGRejectImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);

            String str = xml.get(CUG_REJECT_CAUSE, String.class);
            if (str != null)
                errorMessage.cugRejectCause = Enum.valueOf(CUGRejectCause.class, str);

            errorMessage.extensionContainer = xml.get(MAP_EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MAPErrorMessageCUGRejectImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
            if (errorMessage.getCUGRejectCause() != null)
                xml.add((String) errorMessage.getCUGRejectCause().toString(), CUG_REJECT_CAUSE, String.class);
            xml.add((MAPExtensionContainerImpl) errorMessage.extensionContainer, MAP_EXTENSION_CONTAINER,
                    MAPExtensionContainerImpl.class);
        }
    };

}
