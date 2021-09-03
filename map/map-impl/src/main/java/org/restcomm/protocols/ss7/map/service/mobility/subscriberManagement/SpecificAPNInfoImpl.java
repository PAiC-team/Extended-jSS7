
package org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.APN;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.PDNGWIdentity;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SpecificAPNInfo;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class SpecificAPNInfoImpl extends SequenceBase implements SpecificAPNInfo {

    private static final int _TAG_apn = 0;
    private static final int _TAG_pdnGwIdentity = 1;
    private static final int _TAG_extensionContainer = 2;

    private APN apn;
    private PDNGWIdentity pdnGwIdentity;
    private MAPExtensionContainer extensionContainer;

    public SpecificAPNInfoImpl() {
        super("SpecificAPNInfo");
    }

    public SpecificAPNInfoImpl(APN apn, PDNGWIdentity pdnGwIdentity, MAPExtensionContainer extensionContainer) {
        super("SpecificAPNInfo");
        this.apn = apn;
        this.pdnGwIdentity = pdnGwIdentity;
        this.extensionContainer = extensionContainer;
    }

    @Override
    public APN getAPN() {
        return this.apn;
    }

    @Override
    public PDNGWIdentity getPdnGwIdentity() {
        return this.pdnGwIdentity;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.apn = null;
        this.pdnGwIdentity = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (ais.getTagClass()) {
                case Tag.CLASS_CONTEXT_SPECIFIC: {
                    switch (tag) {
                        case _TAG_apn:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".apn: Parameter is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.apn = new APNImpl();
                            ((APNImpl) this.apn).decodeAll(ais);
                            break;
                        case _TAG_pdnGwIdentity:
                            if (ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".pdnGwIdentity: Parameter is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.pdnGwIdentity = new PDNGWIdentityImpl();
                            ((PDNGWIdentityImpl) this.pdnGwIdentity).decodeAll(ais);
                            break;
                        case _TAG_extensionContainer:
                            if (ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".extensionContainer: Parameter is primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.extensionContainer = new MAPExtensionContainerImpl();
                            ((MAPExtensionContainerImpl) this.extensionContainer).decodeAll(ais);
                            break;
                        default:
                            ais.advanceElement();
                            break;
                    }
                }
                    break;
                default:
                    ais.advanceElement();
                    break;
            }
        }

        if (this.apn == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parament apn is mandatory but does not found", MAPParsingComponentExceptionReason.MistypedParameter);
        }

        if (this.pdnGwIdentity == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parament pdnGwIdentity is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.apn == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName + " the mandatory parameter apn is not defined");
        }
        if (this.pdnGwIdentity == null) {
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + " the mandatory parameter pdnGwIdentity is not defined");
        }

        ((APNImpl) this.apn).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_apn);

        ((PDNGWIdentityImpl) this.pdnGwIdentity).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_pdnGwIdentity);

        if (this.extensionContainer != null)
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                    _TAG_extensionContainer);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.apn != null) {
            sb.append("apn=");
            sb.append(this.apn.toString());
            sb.append(", ");
        }

        if (this.pdnGwIdentity != null) {
            sb.append("pdnGwIdentity=");
            sb.append(this.pdnGwIdentity.toString());
            sb.append(", ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer.toString());
            sb.append(" ");
        }

        sb.append("]");

        return sb.toString();
    }

}
