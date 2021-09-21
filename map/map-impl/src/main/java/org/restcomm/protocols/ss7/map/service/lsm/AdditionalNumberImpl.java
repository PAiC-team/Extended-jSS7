package org.restcomm.protocols.ss7.map.service.lsm;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 *
 *
 * @author amit bhayani
 *
 */
public class AdditionalNumberImpl implements AdditionalNumber, MAPAsnPrimitive {

    private static final int _TAG_MSC_NUMBER = 0;
    private static final int _TAG_SGSN_NUMBER = 1;

    public static final String _PrimitiveName = "AdditionalNumber";

    private ISDNAddressString mSCNumber = null;
    private ISDNAddressString sGSNNumber = null;

    /**
     *
     */
    public AdditionalNumberImpl() {
        super();
    }

    /**
     * @param mscNumber
     * @param sgsnNumber
     */
    public AdditionalNumberImpl(ISDNAddressString mscNumber, ISDNAddressString sgsnNumber) {
        super();
        this.mSCNumber = mscNumber;
        this.sGSNNumber = sgsnNumber;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber#getMSCNumber()
     */
    public ISDNAddressString getMSCNumber() {
        return this.mSCNumber;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.AdditionalNumber#getSGSNNumber()
     */
    public ISDNAddressString getSGSNNumber() {
        return this.sGSNNumber;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getTag()
     */
    public int getTag() throws MAPException {
        if (this.mSCNumber != null) {
            return _TAG_MSC_NUMBER;
        } else {
            return _TAG_SGSN_NUMBER;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getTagClass()
     */
    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#getIsPrimitive()
     */
    public boolean getIsPrimitive() {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#decodeAll
     * (org.mobicents.protocols.asn.AsnInputStream)
     */
    public void decodeAll(AsnInputStream asnInputStream) throws MAPParsingComponentException {
        try {
            int length = asnInputStream.readLength();
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": ", e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": ", e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#decodeData
     * (org.mobicents.protocols.asn.AsnInputStream, int)
     */
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
        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": bad tag class or is not primitive: TagClass=" + asnInputStream.getTagClass(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
            case _TAG_MSC_NUMBER:
                this.mSCNumber = new ISDNAddressStringImpl();
                ((ISDNAddressStringImpl) this.mSCNumber).decodeData(asnInputStream, length);
                break;
            case _TAG_SGSN_NUMBER:
                this.sGSNNumber = new ISDNAddressStringImpl();
                ((ISDNAddressStringImpl) this.sGSNNumber).decodeData(asnInputStream, length);
                break;
            default:
                throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                        + ": Expected msc-Number [0] ISDN-AddressString or sgsn-Number [1] ISDN-AddressString, but found "
                        + asnInputStream.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeAll
     * (org.mobicents.protocols.asn.AsnOutputStream, int, int)
     */
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

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.primitives.MAPAsnPrimitive#encodeData
     * (org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {
        if (this.mSCNumber == null && this.sGSNNumber == null)
            throw new MAPException("Error when encoding " + _PrimitiveName + ": both mscNumber and sgsnNumber must not be null");
        if (this.mSCNumber != null && this.sGSNNumber != null)
            throw new MAPException("Error when encoding " + _PrimitiveName + ": both mscNumber and sgsnNumber must not be not null");

        if (this.mSCNumber != null) {
            ((ISDNAddressStringImpl) this.mSCNumber).encodeData(asnOutputStream);
        } else {
            ((ISDNAddressStringImpl) this.sGSNNumber).encodeData(asnOutputStream);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AdditionalNumber [");

        if (this.mSCNumber != null) {
            sb.append("msc-Number=");
            sb.append(this.mSCNumber.toString());
        }
        if (this.sGSNNumber != null) {
            sb.append("sgsn-Number=");
            sb.append(this.sGSNNumber.toString());
        }

        sb.append("]");

        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mSCNumber == null) ? 0 : mSCNumber.hashCode());
        result = prime * result + ((sGSNNumber == null) ? 0 : sGSNNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AdditionalNumberImpl other = (AdditionalNumberImpl) obj;
        if (mSCNumber == null) {
            if (other.mSCNumber != null)
                return false;
        } else if (!mSCNumber.equals(other.mSCNumber))
            return false;
        if (sGSNNumber == null) {
            if (other.sGSNNumber != null)
                return false;
        } else if (!sGSNNumber.equals(other.sGSNNumber))
            return false;
        return true;
    }

}
