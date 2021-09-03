
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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBGeneralData;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBHPLMNData;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class ODBDataImpl extends SequenceBase implements ODBData {

    private static final long serialVersionUID = 1L;

    private ODBGeneralData oDBGeneralData;
    private ODBHPLMNData odbHplmnData;
    private MAPExtensionContainer extensionContainer;

    public ODBDataImpl() {
        super("ODBData");
    }

    public ODBDataImpl(ODBGeneralData oDBGeneralData, ODBHPLMNData odbHplmnData, MAPExtensionContainer extensionContainer) {
        super("ODBData");
        this.oDBGeneralData = oDBGeneralData;
        this.odbHplmnData = odbHplmnData;
        this.extensionContainer = extensionContainer;
    }

    @Override
    public ODBGeneralData getODBGeneralData() {
        return this.oDBGeneralData;
    }

    @Override
    public ODBHPLMNData getOdbHplmnData() {
        return this.odbHplmnData;
    }

    @Override
    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.oDBGeneralData = null;
        this.odbHplmnData = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
                case 0:
                    if (!ais.isTagPrimitive() || tag != Tag.STRING_BIT)
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".oDBGeneralData: bad tag or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.oDBGeneralData = new ODBGeneralDataImpl();
                    ((ODBGeneralDataImpl) this.oDBGeneralData).decodeAll(ais);
                    break;
                default:
                    switch (ais.getTagClass()) {
                        case Tag.CLASS_UNIVERSAL: {
                            switch (tag) {
                                case Tag.STRING_BIT:
                                    if (!ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".odbHplmnData: Parameter is not primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
                                    this.odbHplmnData = new ODBHPLMNDataImpl();
                                    ((ODBHPLMNDataImpl) this.odbHplmnData).decodeAll(ais);
                                    break;
                                case Tag.SEQUENCE:
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

            num++;
        }

        if (this.oDBGeneralData == null) {
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": Parameter oDBGeneralData is mandatory but does not found",
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }

    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.oDBGeneralData == null)
            throw new MAPException("Error while encoding" + _PrimitiveName + ": oDBGeneralData must not be null");

        ((ODBGeneralDataImpl) this.oDBGeneralData).encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.STRING_BIT);

        if (this.odbHplmnData != null)
            ((ODBHPLMNDataImpl) this.odbHplmnData).encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.STRING_BIT);

        if (this.extensionContainer != null)
            ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(asnOutputStream, Tag.CLASS_UNIVERSAL, Tag.SEQUENCE);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.oDBGeneralData != null) {
            sb.append("oDBGeneralData=");
            sb.append(this.oDBGeneralData.toString());
            sb.append(", ");
        }

        if (this.odbHplmnData != null) {
            sb.append("odbHplmnData=");
            sb.append(this.odbHplmnData.toString());
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
