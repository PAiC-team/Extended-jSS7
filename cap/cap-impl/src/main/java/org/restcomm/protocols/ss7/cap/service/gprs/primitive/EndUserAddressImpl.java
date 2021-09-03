
package org.restcomm.protocols.ss7.cap.service.gprs.primitive;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.EndUserAddress;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPAddress;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPTypeNumber;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPTypeOrganization;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public class EndUserAddressImpl extends SequenceBase implements EndUserAddress {

    public static final int _ID_pdpTypeOrganization = 0;
    public static final int _ID_pdpTypeNumber = 1;
    public static final int _ID_pdpAddress = 2;

    private PDPTypeOrganization pdpTypeOrganization;
    private PDPTypeNumber pdpTypeNumber;
    private PDPAddress pdpAddress;

    public EndUserAddressImpl() {
        super("EndUserAddress");
    }

    public EndUserAddressImpl(PDPTypeOrganization pdpTypeOrganization, PDPTypeNumber pdpTypeNumber, PDPAddress pdpAddress) {
        super("EndUserAddress");
        this.pdpTypeOrganization = pdpTypeOrganization;
        this.pdpTypeNumber = pdpTypeNumber;
        this.pdpAddress = pdpAddress;
    }

    @Override
    public PDPTypeOrganization getPDPTypeOrganization() {
        return this.pdpTypeOrganization;
    }

    @Override
    public PDPTypeNumber getPDPTypeNumber() {
        return this.pdpTypeNumber;
    }

    @Override
    public PDPAddress getPDPAddress() {
        return this.pdpAddress;
    }

    @Override
    protected void _decode(AsnInputStream asnInputStream, int length) throws CAPParsingComponentException, IOException, AsnException {

        this.pdpTypeOrganization = null;
        this.pdpTypeNumber = null;
        this.pdpAddress = null;

        AsnInputStream ais = asnInputStream.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_pdpTypeOrganization:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".pdpTypeOrganization: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.pdpTypeOrganization = new PDPTypeOrganizationImpl();
                        ((PDPTypeOrganizationImpl) this.pdpTypeOrganization).decodeAll(ais);
                        break;
                    case _ID_pdpTypeNumber:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".pdpTypeNumber: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.pdpTypeNumber = new PDPTypeNumberImpl();
                        ((PDPTypeNumberImpl) this.pdpTypeNumber).decodeAll(ais);
                        break;
                    case _ID_pdpAddress:
                        if (!ais.isTagPrimitive())
                            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                                    + ".pdpAddress: Parameter is primitive",
                                    CAPParsingComponentExceptionReason.MistypedParameter);
                        this.pdpAddress = new PDPAddressImpl();
                        ((PDPAddressImpl) this.pdpAddress).decodeAll(ais);
                        break;
                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }

        if (this.pdpTypeOrganization == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": pdpTypeOrganization is mandatory but not found", CAPParsingComponentExceptionReason.MistypedParameter);

        if (this.pdpTypeNumber == null)
            throw new CAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": pdpTypeNumber is mandatory but not found", CAPParsingComponentExceptionReason.MistypedParameter);
    }

    @Override
    public void encodeData(AsnOutputStream asnOutputStream) throws CAPException {

        if (this.pdpTypeOrganization == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": pdpTypeOrganization must not be null");

        if (this.pdpTypeNumber == null)
            throw new CAPException("Error while encoding " + _PrimitiveName + ": pdpTypeNumber must not be null");

        ((PDPTypeOrganizationImpl) this.pdpTypeOrganization).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC,
                _ID_pdpTypeOrganization);

        ((PDPTypeNumberImpl) this.pdpTypeNumber).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_pdpTypeNumber);

        if (this.pdpAddress != null)
            ((PDPAddressImpl) this.pdpAddress).encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, _ID_pdpAddress);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName + " [");

        if (this.pdpTypeOrganization != null) {
            sb.append("pdpTypeOrganization=");
            sb.append(this.pdpTypeOrganization.toString());
            sb.append(", ");
        }

        if (this.pdpTypeNumber != null) {
            sb.append("pdpTypeNumber=");
            sb.append(this.pdpTypeNumber.toString());
            sb.append(", ");
        }

        if (this.pdpAddress != null) {
            sb.append("pdpAddress=");
            sb.append(this.pdpAddress.toString());
        }

        sb.append("]");

        return sb.toString();
    }

}
