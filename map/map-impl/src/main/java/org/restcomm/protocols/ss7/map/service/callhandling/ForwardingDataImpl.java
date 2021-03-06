
package org.restcomm.protocols.ss7.map.service.callhandling;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.FTNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNSubaddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.callhandling.ForwardingData;
import org.restcomm.protocols.ss7.map.api.service.supplementary.ForwardingOptions;
import org.restcomm.protocols.ss7.map.primitives.FTNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.service.supplementary.ForwardingOptionsImpl;

/*
 *
 * @author cristian veliscu
 *
 */
public class ForwardingDataImpl implements ForwardingData, MAPAsnPrimitive {

    private ISDNAddressString forwardedToNumber;
    private ISDNSubaddressString forwardedToSubaddress;
    private ForwardingOptions forwardingOptions;
    private MAPExtensionContainer extensionContainer;
    private FTNAddressString longForwardedToNumber;

    private static final int TAG_forwardedToNumber = 5;
    private static final int TAG_forwardedToSubaddress = 4;
    private static final int TAG_forwardingOptions = 6;
    private static final int TAG_extensionContainer = 7;
    private static final int TAG_longForwardedToNumber = 8;

    private static final String _PrimitiveName = "ForwardingData";

    public ForwardingDataImpl() {
    }

    public ForwardingDataImpl(ISDNAddressString forwardedToNumber, ISDNSubaddressString forwardedToSubaddress,
            ForwardingOptions forwardingOptions, MAPExtensionContainer extensionContainer,
            FTNAddressString longForwardedToNumber) {
        this.forwardedToNumber = forwardedToNumber;
        this.forwardedToSubaddress = forwardedToSubaddress;
        this.forwardingOptions = forwardingOptions;
        this.extensionContainer = extensionContainer;
        this.longForwardedToNumber = longForwardedToNumber;
    }

    @Override
    public ISDNAddressString getForwardedToNumber() {
        return this.forwardedToNumber;
    }

    @Override
    public ISDNSubaddressString getForwardedToSubaddress() {
        return this.forwardedToSubaddress;
    }

    @Override
    public ForwardingOptions getForwardingOptions() {
        return this.forwardingOptions;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    public FTNAddressString getLongForwardedToNumber() {
        return this.longForwardedToNumber;
    }

    @Override
    public int getTag() throws MAPException {
        return Tag.SEQUENCE;
    }

    @Override
    public int getTagClass() {
        return Tag.CLASS_UNIVERSAL;
    }

    @Override
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

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.forwardedToNumber = null;
        this.forwardedToSubaddress = null;
        this.longForwardedToNumber = null;
        this.forwardingOptions = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case TAG_forwardedToNumber:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".forwardedToNumber: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.forwardedToNumber = new ISDNAddressStringImpl();
                        ((ISDNAddressStringImpl) this.forwardedToNumber).decodeAll(ais);
                        break;
                    case TAG_forwardedToSubaddress: // TODO:
                        break;
                    case TAG_forwardingOptions:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".forwardingOptions: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.forwardingOptions = new ForwardingOptionsImpl();
                        ((ForwardingOptionsImpl) this.forwardingOptions).decodeAll(ais);
                        break;
                    case TAG_extensionContainer:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".extensionContainer: Parameter extensionContainer is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                        break;
                    case TAG_longForwardedToNumber:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".longForwardedToNumber: Parameter is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);

                        this.longForwardedToNumber = new FTNAddressStringImpl();
                        ((FTNAddressStringImpl) this.longForwardedToNumber).decodeAll(ais);
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
        if (this.forwardedToNumber != null) {
            ((ISDNAddressStringImpl) this.forwardedToNumber)
                    .encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, TAG_forwardedToNumber);
        }

        if (this.forwardedToSubaddress != null) {
            // TODO:
        }

        if (this.forwardingOptions != null) {
            ((ForwardingOptionsImpl) this.forwardingOptions)
                    .encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, TAG_forwardingOptions);
        }

        if (this.extensionContainer != null) {
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    TAG_extensionContainer);
        }

        if (this.longForwardedToNumber != null) {
            ((FTNAddressStringImpl) this.longForwardedToNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    TAG_longForwardedToNumber);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.forwardedToNumber != null) {
            sb.append("forwardedToNumber=[");
            sb.append(this.forwardedToNumber);
            sb.append("], ");
        }

        if (this.forwardedToSubaddress != null) {
            sb.append("forwardedToSubaddress=[");
            sb.append(this.forwardedToSubaddress);
            sb.append("], ");
        }

        if (this.forwardingOptions != null) {
            sb.append("forwardingOptions=[");
            sb.append(this.forwardingOptions);
            sb.append("], ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=[");
            sb.append(this.extensionContainer);
            sb.append("], ");
        }

        if (this.longForwardedToNumber != null) {
            sb.append("longForwardedToNumber=[");
            sb.append(this.longForwardedToNumber);
            sb.append("]");
        }

        sb.append("]");
        return sb.toString();
    }
}