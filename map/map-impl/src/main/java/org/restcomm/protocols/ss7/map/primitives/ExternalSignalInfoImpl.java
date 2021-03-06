
package org.restcomm.protocols.ss7.map.primitives;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.primitives.ProtocolId;
import org.restcomm.protocols.ss7.map.api.primitives.SignalInfo;

/*
 *
 * @author cristian veliscu
 *
 */
public class ExternalSignalInfoImpl implements ExternalSignalInfo, MAPAsnPrimitive {

    private SignalInfo signalInfo = null;
    private ProtocolId protocolId = null;
    private MAPExtensionContainer extensionContainer = null;

    private static final String _PrimitiveName = "ExternalSignalInfo";

    public ExternalSignalInfoImpl() {
    }

    public ExternalSignalInfoImpl(SignalInfo signalInfo, ProtocolId protocolId, MAPExtensionContainer extensionContainer) {
        this.signalInfo = signalInfo;
        this.protocolId = protocolId;
        this.extensionContainer = extensionContainer;
    }

    @Override
    public SignalInfo getSignalInfo() {
        return this.signalInfo;
    }

    @Override
    public ProtocolId getProtocolId() {
        return this.protocolId;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
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
        this.signalInfo = null;
        this.protocolId = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();
            if (ais.getTagClass() == Tag.CLASS_UNIVERSAL) {
                switch (tag) {
                    case Tag.ENUMERATED:
                        int code = (int) ais.readInteger();
                        this.protocolId = ProtocolId.getProtocolId(code);
                        break;
                    case Tag.STRING_OCTET:
                        if (!ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".signalInfo: Parameter extensionContainer is not primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.signalInfo = new SignalInfoImpl();
                        ((SignalInfoImpl) this.signalInfo).decodeAll(ais);
                        break;
                    case Tag.SEQUENCE:
                        if (ais.isTagPrimitive())
                            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".extensionContainer: Parameter extensionContainer is primitive",
                                    MAPParsingComponentExceptionReason.MistypedParameter);
                        this.extensionContainer = new MAPExtensionContainerImpl();
                        ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            }
        }

        if (this.protocolId == null || this.signalInfo == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": protocolId and signalInfo must not be null", MAPParsingComponentExceptionReason.MistypedParameter);
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
        if (this.protocolId == null || this.signalInfo == null)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": protocolId and signalInfo must not be null");

        try {
            if (this.protocolId != null)
                asnOutputStream.writeInteger(Tag.CLASS_UNIVERSAL, Tag.ENUMERATED, this.protocolId.getCode());
            if (this.signalInfo != null)
                ((SignalInfoImpl) this.signalInfo).encodeAll(asnOutputStream);
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream);
        } catch (IOException e) {
            throw new MAPException("IOException when encoding ExternalSignalInfo : " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding ExternalSignalInfo : " + e.getMessage(), e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.signalInfo != null) {
            sb.append("signalInfo=[");
            sb.append(this.signalInfo);
            sb.append("], ");
        }

        if (this.protocolId != null) {
            sb.append("protocolId=[");
            sb.append(this.protocolId);
            sb.append("], ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=[");
            sb.append(this.extensionContainer);
            sb.append("]");
        }

        sb.append("]");
        return sb.toString();
    }
}