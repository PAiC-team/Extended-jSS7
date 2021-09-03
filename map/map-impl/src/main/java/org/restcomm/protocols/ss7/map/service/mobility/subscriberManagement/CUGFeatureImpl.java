
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
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGFeature;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ExtBasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.InterCUGRestrictions;
import org.restcomm.protocols.ss7.map.primitives.MAPExtensionContainerImpl;
import org.restcomm.protocols.ss7.map.primitives.SequenceBase;

/**
 * @author daniel bichara
 * @author sergey vetyutnev
 *
 */
public class CUGFeatureImpl extends SequenceBase implements CUGFeature {

    private ExtBasicServiceCode basicService = null;
    private Integer preferentialCugIndicator = null;
    private InterCUGRestrictions interCugRestrictions = null;
    private MAPExtensionContainer extensionContainer = null;

    public CUGFeatureImpl() {
        super("CUGFeature");
    }

    /**
     *
     */
    public CUGFeatureImpl(ExtBasicServiceCode basicService, Integer preferentialCugIndicator,
            InterCUGRestrictions interCugRestrictions, MAPExtensionContainer extensionContainer) {
        super("CUGFeature");

        this.basicService = basicService;
        this.preferentialCugIndicator = preferentialCugIndicator;
        this.interCugRestrictions = interCugRestrictions;
        this.extensionContainer = extensionContainer;
    }

    public ExtBasicServiceCode getBasicService() {
        return this.basicService;
    }

    public Integer getPreferentialCugIndicator() {
        return this.preferentialCugIndicator;
    }

    public InterCUGRestrictions getInterCugRestrictions() {
        return this.interCugRestrictions;
    }

    public MAPExtensionContainer getExtensionContainer() {
        return this.extensionContainer;
    }

    protected void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {
        this.basicService = null;
        this.preferentialCugIndicator = null;
        this.interCugRestrictions = null;
        this.extensionContainer = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);

        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            switch (ais.getTagClass()) {
                case Tag.CLASS_CONTEXT_SPECIFIC:
                    switch (tag) {
                        case ExtBasicServiceCodeImpl._ID_ext_BearerService:
                        case ExtBasicServiceCodeImpl._ID_ext_Teleservice:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".basicService: is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.basicService = new ExtBasicServiceCodeImpl();
                            ((ExtBasicServiceCodeImpl) this.basicService).decodeAll(ais);
                            break;
                        default:
                            ais.advanceElement();
                            break;
                    }
                    break;

                case Tag.CLASS_UNIVERSAL:
                    switch (tag) {
                        case Tag.INTEGER:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".preferentialCugIndicator: is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.preferentialCugIndicator = (int) ais.readInteger();
                            break;
                        case Tag.STRING_OCTET:
                            if (!ais.isTagPrimitive())
                                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                                        + ".interCugRestrictions: is not primitive",
                                        MAPParsingComponentExceptionReason.MistypedParameter);
                            this.interCugRestrictions = new InterCUGRestrictionsImpl();
                            ((InterCUGRestrictionsImpl) this.interCugRestrictions).decodeAll(ais);
                            break;
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

        if (this.interCugRestrictions == null)
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": interCugRestrictions required.", MAPParsingComponentExceptionReason.MistypedParameter);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive#encodeData (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        if (this.interCugRestrictions == null)
            throw new MAPException("Error while encoding " + _PrimitiveName + ": interCugRestrictions required.");

        try {

            if (this.basicService != null)
                ((ExtBasicServiceCodeImpl) this.basicService).encodeAll(asnOutputStream);

            if (this.preferentialCugIndicator != null)
                asnOutputStream.writeInteger(this.preferentialCugIndicator);

            ((InterCUGRestrictionsImpl) this.interCugRestrictions).encodeAll(asnOutputStream);

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
        sb.append(_PrimitiveName + " [");

        if (this.basicService != null) {
            sb.append("basicService=");
            sb.append(this.basicService.toString());
            sb.append(", ");
        }

        if (this.preferentialCugIndicator != null) {
            sb.append("preferentialCugIndicator=");
            sb.append(this.preferentialCugIndicator.toString());
            sb.append(", ");
        }

        if (this.interCugRestrictions != null) {
            sb.append("interCugRestrictions=");
            sb.append(this.interCugRestrictions.toString());
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
