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
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageFacilityNotSup;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageFacilityNotSupImpl extends MAPErrorMessageImpl implements MAPErrorMessageFacilityNotSup {

    private static final String SHAPE_OF_LOCATION_ESTIMATE_NOT_SUPPORTED = "shapeOfLocationEstimateNotSupported";
    private static final String NEEDED_LCS_CAPABILITY_NOT_SUPPORTED = "neededLcsCapabilityNotSupportedInServingNode";
    private static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";

    public static final int shapeOfLocationEstimateNotSupported_TAG = 0;
    public static final int neededLcsCapabilityNotSupportedInServingNode_TAG = 1;

    private MAPExtensionContainer extensionContainer;
    private Boolean shapeOfLocationEstimateNotSupported;
    private Boolean neededLcsCapabilityNotSupportedInServingNode;

    public MAPErrorMessageFacilityNotSupImpl(MAPExtensionContainer extensionContainer,
            Boolean shapeOfLocationEstimateNotSupported, Boolean neededLcsCapabilityNotSupportedInServingNode) {
        super((long) MAPErrorCode.facilityNotSupported);

        this.extensionContainer = extensionContainer;
        this.shapeOfLocationEstimateNotSupported = shapeOfLocationEstimateNotSupported;
        this.neededLcsCapabilityNotSupportedInServingNode = neededLcsCapabilityNotSupportedInServingNode;
    }

    public MAPErrorMessageFacilityNotSupImpl() {
        super((long) MAPErrorCode.facilityNotSupported);
    }

    public boolean isEmFacilityNotSup() {
        return true;
    }

    public MAPErrorMessageFacilityNotSup getEmFacilityNotSup() {
        return this;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    public Boolean getShapeOfLocationEstimateNotSupported() {
        return this.shapeOfLocationEstimateNotSupported;
    }

    public Boolean getNeededLcsCapabilityNotSupportedInServingNode() {
        return this.neededLcsCapabilityNotSupportedInServingNode;
    }

    public void setExtensionContainer(MAPExtensionContainer extensionContainer) {
        this.extensionContainer = extensionContainer;
    }

    public void setShapeOfLocationEstimateNotSupported(Boolean shapeOfLocationEstimateNotSupported) {
        this.shapeOfLocationEstimateNotSupported = shapeOfLocationEstimateNotSupported;
    }

    public void getNeededLcsCapabilityNotSupportedInServingNode(Boolean neededLcsCapabilityNotSupportedInServingNode) {
        this.neededLcsCapabilityNotSupportedInServingNode = neededLcsCapabilityNotSupportedInServingNode;
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
            throw new MAPParsingComponentException(
                    "IOException when decoding MAPErrorMessageFacilityNotSup: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageFacilityNotSup: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException(
                    "IOException when decoding MAPErrorMessageFacilityNotSup: " + e.getMessage(), e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding MAPErrorMessageFacilityNotSup: "
                    + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream localAsnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.extensionContainer = null;
        this.shapeOfLocationEstimateNotSupported = null;
        this.neededLcsCapabilityNotSupportedInServingNode = null;

        if (localAsnInputStream.getTagClass() != Tag.CLASS_UNIVERSAL || localAsnInputStream.getTag() != Tag.SEQUENCE
            || localAsnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException(
                    "Error decoding MAPErrorMessageFacilityNotSup: bad tag class or tag or parameter is primitive or no parameter data",
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

                        default:
                            ais.advanceElement();
                            break;
                    }
                    break;

                case Tag.CLASS_CONTEXT_SPECIFIC:
                    switch (tag) {
                        case shapeOfLocationEstimateNotSupported_TAG:
                            ais.readNull();
                            this.shapeOfLocationEstimateNotSupported = true;
                            break;

                        case neededLcsCapabilityNotSupportedInServingNode_TAG:
                            ais.readNull();
                            this.neededLcsCapabilityNotSupportedInServingNode = true;
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

        if (this.shapeOfLocationEstimateNotSupported == null)
            this.shapeOfLocationEstimateNotSupported = false;
        if (this.neededLcsCapabilityNotSupportedInServingNode == null)
            this.neededLcsCapabilityNotSupportedInServingNode = false;
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
            throw new MAPException("AsnException when encoding MAPErrorMessageFacilityNotSup: " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.extensionContainer == null
                && (this.shapeOfLocationEstimateNotSupported == null || this.shapeOfLocationEstimateNotSupported == false)
                && (this.neededLcsCapabilityNotSupportedInServingNode == null || this.neededLcsCapabilityNotSupportedInServingNode == false))
            return;

        try {
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);

            if (this.shapeOfLocationEstimateNotSupported != null && this.shapeOfLocationEstimateNotSupported == true)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, shapeOfLocationEstimateNotSupported_TAG);
            if (this.neededLcsCapabilityNotSupportedInServingNode != null
                    && this.neededLcsCapabilityNotSupportedInServingNode == true)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, neededLcsCapabilityNotSupportedInServingNode_TAG);

        } catch (IOException e) {
            throw new MAPException("IOException when encoding MAPErrorMessageFacilityNotSup: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding MAPErrorMessageFacilityNotSup: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MAPErrorMessageFacilityNotSup [");
        if (this.extensionContainer != null)
            sb.append("extensionContainer=" + this.extensionContainer.toString());
        if (this.shapeOfLocationEstimateNotSupported != null && this.shapeOfLocationEstimateNotSupported == true)
            sb.append(", shapeOfLocationEstimateNotSupported=true");
        if (this.neededLcsCapabilityNotSupportedInServingNode != null
                && this.neededLcsCapabilityNotSupportedInServingNode == true)
            sb.append(", neededLcsCapabilityNotSupportedInServingNode=true");
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageFacilityNotSupImpl> MAP_ERROR_MESSAGE_FACILITY_NOT_SUPPORTED_XML = new XMLFormat<MAPErrorMessageFacilityNotSupImpl>(
            MAPErrorMessageFacilityNotSupImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageFacilityNotSupImpl ussdMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, ussdMessage);
            ussdMessage.shapeOfLocationEstimateNotSupported = xml.get(SHAPE_OF_LOCATION_ESTIMATE_NOT_SUPPORTED, Boolean.class);
            ussdMessage.neededLcsCapabilityNotSupportedInServingNode = xml.get(NEEDED_LCS_CAPABILITY_NOT_SUPPORTED,
                    Boolean.class);
            ussdMessage.extensionContainer = xml.get(MAP_EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MAPErrorMessageFacilityNotSupImpl ussdMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(ussdMessage, xml);
            xml.add(ussdMessage.getShapeOfLocationEstimateNotSupported(), SHAPE_OF_LOCATION_ESTIMATE_NOT_SUPPORTED,
                    Boolean.class);
            xml.add(ussdMessage.getNeededLcsCapabilityNotSupportedInServingNode(), NEEDED_LCS_CAPABILITY_NOT_SUPPORTED,
                    Boolean.class);
            xml.add((MAPExtensionContainerImpl) ussdMessage.extensionContainer, MAP_EXTENSION_CONTAINER,
                    MAPExtensionContainerImpl.class);
        }
    };
}
