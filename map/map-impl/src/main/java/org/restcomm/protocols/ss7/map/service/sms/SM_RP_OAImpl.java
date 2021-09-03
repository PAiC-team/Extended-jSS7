
package org.restcomm.protocols.ss7.map.service.sms;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_OA;
import org.restcomm.protocols.ss7.map.primitives.AddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SM_RP_OAImpl implements SM_RP_OA, MAPAsnPrimitive {

    private static final int _TAG_Msisdn = 2;
    private static final int _TAG_ServiceCentreAddressOA = 4;
    private static final int _TAG_noSM_RP_OA = 5;

    public static final String _PrimitiveName = "SM_RP_OA";

    private ISDNAddressString msisdn;
    private AddressString serviceCentreAddressOA;

    public SM_RP_OAImpl() {
    }

    public void setMsisdn(ISDNAddressString msisdn) {
        this.msisdn = msisdn;
    }

    public void setServiceCentreAddressOA(AddressString serviceCentreAddressOA) {
        this.serviceCentreAddressOA = serviceCentreAddressOA;
    }

    public ISDNAddressString getMsisdn() {
        return this.msisdn;
    }

    public AddressString getServiceCentreAddressOA() {
        return this.serviceCentreAddressOA;
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    public int getTag() {
        if (this.msisdn != null)
            return _TAG_Msisdn;
        else if (this.serviceCentreAddressOA != null)
            return _TAG_ServiceCentreAddressOA;
        else
            return _TAG_noSM_RP_OA;
    }

    public boolean getIsPrimitive() {
        return true;
    }

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
        this.msisdn = null;
        this.serviceCentreAddressOA = null;

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": bad tag class or is not primitive: TagClass=" + asnInputStream.getTagClass(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
            case _TAG_Msisdn:
                this.msisdn = new ISDNAddressStringImpl();
                ((ISDNAddressStringImpl) this.msisdn).decodeData(asnInputStream, length);
                break;

            case _TAG_ServiceCentreAddressOA:
                this.serviceCentreAddressOA = new AddressStringImpl();
                ((AddressStringImpl) this.serviceCentreAddressOA).decodeData(asnInputStream, length);
                break;

            case _TAG_noSM_RP_OA:
                try {
                    asnInputStream.readNullData(length);
                } catch (AsnException e) {
                    throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": "
                            + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
                } catch (IOException e) {
                    throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": "
                            + e.getMessage(), e, MAPParsingComponentExceptionReason.MistypedParameter);
                }
                break;

            default:
                throw new MAPParsingComponentException("Error while " + _PrimitiveName + ": bad tag: " + asnInputStream.getTag(),
                        MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, Tag.CLASS_CONTEXT_SPECIFIC, this.getTag());
    }

    public void encodeAll(AsnOutputStream asnOutputStream, int tagClass, int tag) throws MAPException {
        try {
            asnOutputStream.writeTag(tagClass, true, tag);
            int pos = asnOutputStream.StartContentDefiniteLength();
            this.encodeData(asnOutputStream);
            asnOutputStream.FinalizeContent(pos);
        } catch (AsnException e) {
            throw new MAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.msisdn != null)
            ((ISDNAddressStringImpl) this.msisdn).encodeData(asnOutputStream);
        else if (this.serviceCentreAddressOA != null)
            ((AddressStringImpl) this.serviceCentreAddressOA).encodeData(asnOutputStream);
        else
            asnOutputStream.writeNullData();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.msisdn != null) {
            sb.append("msisdn=");
            sb.append(this.msisdn.toString());
        }
        if (this.serviceCentreAddressOA != null) {
            sb.append("serviceCentreAddressOA=");
            sb.append(this.serviceCentreAddressOA.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
