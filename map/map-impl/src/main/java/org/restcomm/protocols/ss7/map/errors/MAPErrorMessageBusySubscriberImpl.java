
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
import org.restcomm.protocols.ss7.map.api.errors.MAPErrorMessageBusySubscriber;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;

/**
 *
 * @author sergey vetyutnev
 * @author amit bhayani
 */
public class MAPErrorMessageBusySubscriberImpl extends MAPErrorMessageImpl implements MAPErrorMessageBusySubscriber {

    private static final String CCBS_POSSIBLE = "ccbsPossible";
    private static final String CCBS_BUSY = "ccbsBusy";
    private static final String MAP_EXTENSION_CONTAINER = "mapExtensionContainer";

    public static final int _tag_ccbs_Possible = 0;
    public static final int _tag_ccbs_Busy = 1;

    private MAPExtensionContainer extensionContainer;
    private boolean ccbsPossible;
    private boolean ccbsBusy;

    protected String _PrimitiveName = "MAPErrorMessageBusySubscriber";

    public MAPErrorMessageBusySubscriberImpl(MAPExtensionContainer extensionContainer, boolean ccbsPossible, boolean ccbsBusy) {
        super((long) MAPErrorCode.busySubscriber);

        this.extensionContainer = extensionContainer;
        this.ccbsPossible = ccbsPossible;
        this.ccbsBusy = ccbsBusy;
    }

    public MAPErrorMessageBusySubscriberImpl() {
        super((long) MAPErrorCode.busySubscriber);
    }

    public boolean isEmBusySubscriber() {
        return true;
    }

    public MAPErrorMessageBusySubscriber getEmBusySubscriber() {
        return this;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return extensionContainer;
    }

    @Override
    public boolean getCcbsPossible() {
        return ccbsPossible;
    }

    @Override
    public boolean getCcbsBusy() {
        return ccbsBusy;
    }

    @Override
    public void setExtensionContainer(MAPExtensionContainer val) {
        this.extensionContainer = val;
    }

    @Override
    public void setCcbsPossible(boolean val) {
        this.ccbsPossible = val;
    }

    @Override
    public void setCcbsBusy(boolean val) {
        this.ccbsBusy = val;
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
        this.extensionContainer = null;
        this.ccbsPossible = false;
        this.ccbsBusy = false;

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

                        default:
                            ais.advanceElement();
                            break;
                    }
                    break;

                case Tag.CLASS_CONTEXT_SPECIFIC:
                    switch (tag) {
                        case _tag_ccbs_Possible:
                            ais.readNull();
                            this.ccbsPossible = true;
                            break;
                        case _tag_ccbs_Busy:
                            ais.readNull();
                            this.ccbsBusy = true;
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

        if (this.ccbsPossible == false && this.ccbsBusy == false && this.extensionContainer == null)
            return;

        try {
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
            if (this.ccbsPossible)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _tag_ccbs_Possible);
            if (this.ccbsBusy)
                asnOutputStream.writeNull(Tag.CLASS_CONTEXT_SPECIFIC, _tag_ccbs_Busy);

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

        if (this.extensionContainer != null)
            sb.append("extensionContainer=" + this.extensionContainer.toString());
        if (this.ccbsPossible)
            sb.append(", ccbsPossible");
        if (this.ccbsBusy)
            sb.append(", ccbsBusy");
        sb.append("]");

        return sb.toString();
    }

    /**
     * XML Serialization/Deserialization
     */
    protected static final XMLFormat<MAPErrorMessageBusySubscriberImpl> MAP_ERROR_MESSAGE_BUSY_SUBSCRIBER_XML = new XMLFormat<MAPErrorMessageBusySubscriberImpl>(
            MAPErrorMessageBusySubscriberImpl.class) {

        @Override
        public void read(javolution.xml.XMLFormat.InputElement xml, MAPErrorMessageBusySubscriberImpl errorMessage)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.read(xml, errorMessage);
            errorMessage.ccbsPossible = xml.get(CCBS_POSSIBLE, Boolean.class);
            errorMessage.ccbsBusy = xml.get(CCBS_BUSY, Boolean.class);
            errorMessage.extensionContainer = xml.get(MAP_EXTENSION_CONTAINER, MAPExtensionContainerImpl.class);
        }

        @Override
        public void write(MAPErrorMessageBusySubscriberImpl errorMessage, javolution.xml.XMLFormat.OutputElement xml)
                throws XMLStreamException {
            MAP_ERROR_MESSAGE_XML.write(errorMessage, xml);
            xml.add(errorMessage.getCcbsPossible(), CCBS_POSSIBLE, Boolean.class);
            xml.add(errorMessage.getCcbsBusy(), CCBS_BUSY, Boolean.class);
            xml.add((MAPExtensionContainerImpl) errorMessage.extensionContainer, MAP_EXTENSION_CONTAINER,
                    MAPExtensionContainerImpl.class);
        }
    };

}
