
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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.EMLPPInfo;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 * @author daniel bichara
 *
 */
public class EMLPPInfoImpl extends SequenceBase implements EMLPPInfo {

    private int maximumEntitledPriority = 0;
    private int defaultPriority = 0;
    private MAPExtensionContainer extensionContainer = null;

    public EMLPPInfoImpl() {
        super("EMLPPInfo");
    }

    /**
     *
     */
    public EMLPPInfoImpl(int maximumEntitledPriority, int defaultPriority, MAPExtensionContainer extensionContainer) {
        super("EMLPPInfo");

        this.maximumEntitledPriority = maximumEntitledPriority;
        this.defaultPriority = defaultPriority;
        this.extensionContainer = extensionContainer;
    }

    public int getMaximumEntitledPriority() {
        return this.maximumEntitledPriority;
    }

    public int getDefaultPriority() {
        return this.defaultPriority;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.maximumEntitledPriority = 0;
        this.defaultPriority = 0;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        int num = 0;
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (num) {
                case 0: // maximumEntitledPriority
                    if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || tag != Tag.INTEGER || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".maximumEntitledPriority: bad tag or tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.maximumEntitledPriority = (int) ais.readInteger();
                    break;

                case 1: // defaultPriority
                    if (ais.getTagClass() != Tag.CLASS_UNIVERSAL || tag != Tag.INTEGER || !ais.isTagPrimitive())
                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                + ".defaultPriority: bad tag or tag class or not primitive",
                                MAPParsingComponentExceptionReason.MistypedParameter);
                    this.defaultPriority = (int) ais.readInteger();
                    break;

                default:
                    switch (ais.getTagClass()) {
                        case Tag.CLASS_UNIVERSAL:
                            switch (tag) {
                                case Tag.SEQUENCE:
                                    if (ais.isTagPrimitive())
                                        throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                                + ".extensionContainer: is primitive",
                                                MAPParsingComponentExceptionReason.MistypedParameter);
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
            num++;
        }

        if (num < 2)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": maximumEntitledPriority and defaultPriority required but not found.",
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (this.maximumEntitledPriority < 0 || this.maximumEntitledPriority > 15)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ".maximumEntitledPriority: must be from 0 to 15, found:" + this.maximumEntitledPriority,
                    MAPParsingComponentExceptionReason.MistypedParameter);

        if (this.defaultPriority < 0 || this.defaultPriority > 15)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ".defaultPriority: must be from 0 to 15, found:" + this.defaultPriority,
                    MAPParsingComponentExceptionReason.MistypedParameter);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeData (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream maximumEntitledPriority) throws MAPException {

        if (this.maximumEntitledPriority < 0 || this.maximumEntitledPriority > 15)
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + ": maximumEntitledPriority required value from 0 to 15.");

        if (this.defaultPriority < 0 || this.defaultPriority > 15)
            throw new MAPException("Error while encoding " + _PrimitiveName
                    + ": maximumEntitledPriority required value from 0 to 15.");

        try {
            maximumEntitledPriority.writeInteger(this.maximumEntitledPriority);

            maximumEntitledPriority.writeInteger(this.defaultPriority);

            if (this.extensionContainer != null)
                ((MAPExtensionContainerImpl) this.extensionContainer).encodeAll(maximumEntitledPriority);
        } catch (IOException e) {
            throw new MAPException("IOException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        sb.append("maximumEntitledPriority=");
        sb.append(this.maximumEntitledPriority);
        sb.append(", ");

        sb.append("defaultPriority=");
        sb.append(this.defaultPriority);
        sb.append(", ");

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer.toString());
            sb.append(", ");
        }

        sb.append("]");

        return sb.toString();
    }

}
