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
import org.restcomm.protocols.ss7.map.api.errors.CallBarringCause;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorCode;
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageCallBarred;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageCallBarredImpl extends MAPErrorMessageImpl implements MAPErrorMessageCallBarred {

    private static final String MAP_PROTOCOL_VERSION = "mapProtocolVersion";
    private static final String CALL_BARRING_CAUSE = "callBarringCause";
    private static final String UNAUTHORISED_MESSAGE_ORIGINATOR = "unauthorisedMessageOriginator";
    private static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";

    public static final int unauthorisedMessageOriginator_TAG = 1;

    private long mapProtocolVersion;
    private CallBarringCause callBarringCause;
    private MAPExtensionContainer extensionContainer;
    private Boolean unauthorisedMessageOriginator;

    public MAPErrorMessageCallBarredImpl(long mapProtocolVersion, CallBarringCause callBarringCause,
            MAPExtensionContainer extensionContainer, Boolean unauthorisedMessageOriginator) {
        super((long) MAPErrorCode.callBarred);

        this.mapProtocolVersion = mapProtocolVersion;
        this.callBarringCause = callBarringCause;
        this.extensionContainer = extensionContainer;
        this.unauthorisedMessageOriginator = unauthorisedMessageOriginator;
    }

    public MAPErrorMessageCallBarredImpl() {
        super((long) MAPErrorCode.callBarred);
    }

    public boolean isEmCallBarred() {
        return true;
    }

    public MAPErrorMessageCallBarred getEmCallBarred() {
        return this;
    }

    public CallBarringCause getCallBarringCause() {
        return this.callBarringCause;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public Boolean getUnauthorisedMessageOriginator() {
        return this.unauthorisedMessageOriginator;
    }

    public long getMapProtocolVersion() {
        return this.mapProtocolVersion;
    }

    public void setCallBarringCause(CallBarringCause callBarringCause) {
        this.callBarringCause = callBarringCause;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public void setUnauthorisedMessageOriginator(Boolean unauthorisedMessageOriginator) {
        this.unauthorisedMessageOriginator = unauthorisedMessageOriginator;
    }

    public void setMapProtocolVersion(long mapProtocolVersion) {
        this.mapProtocolVersion = mapProtocolVersion;
    }

    public int getTag() throws MAPException {
        if (this.mapProtocolVersion < 3)
            return Tag.ENUMERATED;
        else
            return Tag.SEQUENCE;
    }

    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    public boolean getIsPrimitive() {
        if (this.mapProtocolVersion < 3)
            return true;
        else
            return false;
    }

    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageCallBarred: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageCallBarred: " + e.getMessage(),
                    e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding MAPErrorMessageCallBarred: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageCallBarred: " + e.getMessage(),
                    e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream localAsnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.callBarringCause = null;
        this.unauthorisedMessageOriginator = null;
        this.extensionContainer = null;

        if (localAsnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL)
            throw new MAPParsingComponentException("Error decoding MAPErrorMessageCallBarred: bad tag class",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (localAsnInputStream.getTag()) {
            case Tag.ENUMERATED:
                if (!localAsnInputStream.isTagPrimitive())
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageCallBarred: ENUMERATED tag but data is not primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                int code = (int) localAsnInputStream.readIntegerData(length);
                this.callBarringCause = CallBarringCause.getInstance(code);
                if (this.callBarringCause == null)
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageCallBarred.callBarringCause: bad code value",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                this.mapProtocolVersion = 2;
                break;

            case Tag.SEQUENCE:
                if (localAsnInputStream.isTagPrimitive())
                    throw new MAPParsingComponentException(
                            "Error decoding MAPErrorMessageCallBarred: SEQUENCE tag but data is primitive",
                            MAPParsingComponentExceptionReason.MistypedParameter);
                AsnInputStream ais = localAsnInputStream.readSequenceStreamData(length);

                while (true) {
                    if (ais.available() == 0)
                        break;

                    int tag = ais.readTag();

                    switch (ais.getTagClass()) {
                        case Tag.CLASS_UNIVERSAL:
                            switch (tag) {
                                case Tag.ENUMERATED:
                                    code = (int) ais.readInteger();
                                    this.callBarringCause = CallBarringCause.getInstance(code);
                                    if (this.callBarringCause == null)
                                        throw new MAPParsingComponentException(
                                                "Error decoding MAPErrorMessageCallBarred.callBarringCause: bad code value",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    break;

                                case Tag.SEQUENCE:
                                    this.extensionContainer = new MAPExtensionContainerImpl();
                                    ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                                    break;

                                default:
                                    ais.advanceElement();
                                    break;
                            }
                            break;

                        case Tag.CLASS_CONTEXT_SPECIFIC:
                            switch (tag) {
                                case unauthorisedMessageOriginator_TAG:
                                    ais.readNull();
                                    this.unauthorisedMessageOriginator = true;
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

                this.mapProtocolVersion = 3;
                break;

            default:
                throw new MAPParsingComponentException("Error decoding MAPErrorMessageCallBarred: bad tag",
                        MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.unauthorisedMessageOriginator == null)
            this.unauthorisedMessageOriginator = false;
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.mapProtocolVersion < 3)
            this.encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.ENUMERATED);
        else
            this.encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            if (this.mapProtocolVersion < 3)
                asnOutputStream.writeTag(tagClass, true, tag);
            else
                asnOutputStream.writeTag(tagClass, false, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageCallBarred: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.callBarringCause == null
                && (this.unauthorisedMessageOriginator == null || this.unauthorisedMessageOriginator == false)
                && this.extensionContainer == null)
            return;
        if (this.callBarringCause == null && this.mapProtocolVersion < 3)
            return;

        try {
            if (this.mapProtocolVersion < 3) {
                asnOutputStream.writeIntegerData(this.callBarringCause.getCode());
            } else {
                if (this.callBarringCause != null)
                    asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.callBarringCause.getCode());
                if (this.extensionContainer != null)
                    ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
                if (this.unauthorisedMessageOriginator != null && this.unauthorisedMessageOriginator == true)
                    asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, unauthorisedMessageOriginator_TAG);
            }

        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPErrorMessageCallBarred: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageCallBarred: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MAPErrorMessageCallBarred [");
        if (this.callBarringCause != null)
            sb.append("callBarringCause=" + this.callBarringCause.toString());
        if (this.extensionContainer != null)
            sb.append(", extensionContainer=" + this.extensionContainer.toString());
        if (this.unauthorisedMessageOriginator != null && this.unauthorisedMessageOriginator == true)
            sb.append(", unauthorisedMessageOriginator=true");
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageCallBarredImpl> MAP_ERROR_MESSAGE_CALL_BARRED_XML = new XMLFormat<MAPErrorMessageCallBarredImpl>(
            MAPErrorMessageCallBarredImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageCallBarredImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
            errorMessage.mapProtocolVersion = xml.get(MAP_PROTOCOL_VERSION, Long.class);

            String str = xml.get(CALL_BARRING_CAUSE, String.class);
            if (str != null)
                errorMessage.callBarringCause = Enum.valueOf(CallBarringCause.class, str);

            errorMessage.unauthorisedMessageOriginator = xml.get(UNAUTHORISED_MESSAGE_ORIGINATOR, Boolean.class);
            errorMessage.extensionContainer = xml.get(MAP_EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MAPErrorMessageCallBarredImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
            xml.add(errorMessage.getMapProtocolVersion(), MAP_PROTOCOL_VERSION, Long.class);
            if (errorMessage.getCallBarringCause() != null)
                xml.add((String) errorMessage.getCallBarringCause().toString(), CALL_BARRING_CAUSE, String.class);
            xml.add(errorMessage.getUnauthorisedMessageOriginator(), UNAUTHORISED_MESSAGE_ORIGINATOR, Boolean.class);
            xml.add((MAPExtensionContainerImpl) errorMessage.extensionContainer, MAP_EXTENSION_CONTAINER,
                    MAPExtensionContainerImpl.class);
        }
    };
}
