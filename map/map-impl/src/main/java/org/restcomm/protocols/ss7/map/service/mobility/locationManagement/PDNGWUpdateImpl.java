
package org.restcomm.protocols.ss7.map.service.mobility.locationManagement;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement.PDNGWUpdate;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.APN;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.PDNGWIdentity;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.APNImpl;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberManagement.PDNGWIdentityImpl;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class PDNGWUpdateImpl extends SequenceBase implements PDNGWUpdate {

    public static final int _TAG_apn = 0;
    public static final int _TAG_pdnGwIdentity = 1;
    public static final int _TAG_contextId = 2;
    public static final int _TAG_extensionContainer = 3;

    private APN apn;
    private PDNGWIdentity pdnGwIdentity;
    private Integer contextId;
    private MAPExtensionContainer extensionContainer;

    public PDNGWUpdateImpl() {
        super("PDNGWUpdate");
    }

    public PDNGWUpdateImpl(APN apn, PDNGWIdentity pdnGwIdentity, Integer contextId, MAPExtensionContainer extensionContainer) {
        super("PDNGWUpdate");
        this.apn = apn;
        this.pdnGwIdentity = pdnGwIdentity;
        this.contextId = contextId;
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
    public Integer getContextId() {
        return this.contextId;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.apn = null;
        this.pdnGwIdentity = null;
        this.contextId = null;
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
                        case _TAG_contextId:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".contextId: Parameter is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            int cntxId = (int) ais.readInteger();
                            this.contextId = new Integer(cntxId);
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
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        try {
            if (this.apn != null)
                ((APNImpl) this.apn).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_apn);

            if (this.pdnGwIdentity != null)
                ((PDNGWIdentityImpl) this.pdnGwIdentity).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _TAG_pdnGwIdentity);

            if (this.contextId != null) {
                asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG_contextId, this.contextId.intValue());
            }
            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                        _TAG_extensionContainer);

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

        if (this.contextId != null) {
            sb.append("contextId=");
            sb.append(this.contextId.toString());
            sb.append(", ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
