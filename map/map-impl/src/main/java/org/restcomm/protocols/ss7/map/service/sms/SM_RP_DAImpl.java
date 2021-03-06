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
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.service.sms.SM_RP_DA;
import org.restcomm.protocols.ss7.map.primitives.AddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.IMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.LMSIImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class SM_RP_DAImpl implements SM_RP_DA, MAPAsnPrimitive {

    private static final int _TAG_IMSI = 0;
    private static final int _TAG_LMSI = 1;
    private static final int _TAG_ServiceCentreAddressDA = 4;
    private static final int _TAG_NoSM_RP_DA = 5;

    public static final String _PrimitiveName = "SM_RP_DA";

    private IMSI imsi;
    private LMSI lmsi;
    private AddressString serviceCentreAddressDA;

    public SM_RP_DAImpl() {
    }

    public SM_RP_DAImpl(IMSI imsi) {
        this.imsi = imsi;
    }

    public SM_RP_DAImpl(LMSI lmsi) {
        this.lmsi = lmsi;
    }

    public SM_RP_DAImpl(AddressString serviceCentreAddressDA) {
        this.serviceCentreAddressDA = serviceCentreAddressDA;
    }

    public IMSI getIMSI() {
        return this.imsi;
    }

    public LMSI getLMSI() {
        return this.lmsi;
    }

    public AddressString getServiceCentreAddressDA() {
        return serviceCentreAddressDA;
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    public int getTag() {
        if (this.imsi != null)
            return _TAG_IMSI;
        else if (this.lmsi != null)
            return _TAG_LMSI;
        else if (this.serviceCentreAddressDA != null)
            return _TAG_ServiceCentreAddressDA;
        else
            return _TAG_NoSM_RP_DA;
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
        this.imsi = null;
        this.lmsi = null;
        this.serviceCentreAddressDA = null;

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": bad tag class or is not primitive: TagClass=" + asnInputStream.getTagClass(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
            case _TAG_IMSI:
                this.imsi = new IMSIImpl();
                ((IMSIImpl) this.imsi).decodeData(asnInputStream, length);
                break;

            case _TAG_LMSI:
                this.lmsi = new LMSIImpl();
                ((LMSIImpl) this.lmsi).decodeData(asnInputStream, length);
                break;

            case _TAG_ServiceCentreAddressDA:
                this.serviceCentreAddressDA = new AddressStringImpl();
                ((AddressStringImpl) this.serviceCentreAddressDA).decodeData(asnInputStream, length);
                break;

            case _TAG_NoSM_RP_DA:
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
                throw new MAPParsingComponentException("Error while SM_RP_DA: bad tag: " + asnInputStream.getTag(),
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
        if (this.imsi != null)
            ((IMSIImpl) this.imsi).encodeData(asnOutputStream);
        else if (this.lmsi != null)
            ((LMSIImpl) this.lmsi).encodeData(asnOutputStream);
        else if (this.serviceCentreAddressDA != null)
            ((AddressStringImpl) this.serviceCentreAddressDA).encodeData(asnOutputStream);
        else {
            asnOutputStream.writeNullData();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SM_RP_DA [");

        if (this.imsi != null) {
            sb.append("imsi=");
            sb.append(this.imsi.toString());
        }
        if (this.lmsi != null) {
            sb.append("lmsi=");
            sb.append(this.lmsi.toString());
        }
        if (this.serviceCentreAddressDA != null) {
            sb.append("serviceCentreAddressDA=");
            sb.append(this.serviceCentreAddressDA.toString());
        }

        sb.append("]");

        return sb.toString();
    }
}
