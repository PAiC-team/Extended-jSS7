
package org.restcomm.protocols.ss7.map.service.lsm;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.restcomm.protocols.ss7.map.api.primitives.DiameterIdentity;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.lsm.ServingNodeAddress;
import org.restcomm.protocols.ss7.map.primitives.DiameterIdentityImpl;
import org.restcomm.protocols.ss7.map.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 *
 * @author sergey vetyutnev
 *
 */
public class ServingNodeAddressImpl implements ServingNodeAddress, MAPAsnPrimitive {

    public static final int _TAG_mscNumber = 0;
    public static final int _TAG_sgsnNumber = 1;
    public static final int _TAG_mmeNumber = 2;

    public static final String _PrimitiveName = "ProvideSubscriberLocationResponse";

    private ISDNAddressString mscNumber;
    private ISDNAddressString sgsnNumber;
    private DiameterIdentity mmeNumber;

    public ServingNodeAddressImpl() {
    }

    public ServingNodeAddressImpl(ISDNAddressString isdnNumber, boolean isMsc) {
        if (isMsc)
            this.mscNumber = isdnNumber;
        else
            this.sgsnNumber = isdnNumber;
    }

    public ServingNodeAddressImpl(DiameterIdentity mmeNumber) {
        this.mmeNumber = mmeNumber;
    }

    public ISDNAddressString getMscNumber() {
        return mscNumber;
    }

    public ISDNAddressString getSgsnNumber() {
        return sgsnNumber;
    }

    public DiameterIdentity getMmeNumber() {
        return mmeNumber;
    }

    public int getTag() throws MAPException {
        if (this.mscNumber != null) {
            return _TAG_mscNumber;
        } else if (this.sgsnNumber != null) {
            return _TAG_sgsnNumber;
        } else {
            return _TAG_mmeNumber;
        }
    }

    public int getTagClass() {
        return Tag.CLASS_CONTEXT_SPECIFIC;
    }

    public boolean getIsPrimitive() {
        return true;
    }

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

    public void decodeData(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException {
        try {
            this._decode(asnInputStream, length);
        } catch (IOException e) {
            throw new MAPParsingComponentException("IOException when decoding " + _PrimitiveName + ": ", e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        } catch (AsnException e) {
            throw new MAPParsingComponentException("AsnException when decoding " + _PrimitiveName + ": ", e,
                    MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    private void _decode(AsnInputStream asnInputStream, int length) throws MAPParsingComponentException, IOException, AsnException {

        if (asnInputStream.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC || !asnInputStream.isTagPrimitive())
            throw new MAPParsingComponentException("Error while decoding " + _PrimitiveName
                    + ": bad tag class or is not primitive: TagClass=" + asnInputStream.getTagClass(),
                    MAPParsingComponentExceptionReason.MistypedParameter);

        switch (asnInputStream.getTag()) {
            case _TAG_mscNumber:
                this.mscNumber = new ISDNAddressStringImpl();
                ((ISDNAddressStringImpl) this.mscNumber).decodeData(asnInputStream, length);
                break;
            case _TAG_sgsnNumber:
                this.sgsnNumber = new ISDNAddressStringImpl();
                ((ISDNAddressStringImpl) this.sgsnNumber).decodeData(asnInputStream, length);
                break;
            case _TAG_mmeNumber:
                this.mmeNumber = new DiameterIdentityImpl();
                ((DiameterIdentityImpl) this.mmeNumber).decodeData(asnInputStream, length);
                break;
            default:
                throw new MAPParsingComponentException(
                        "Error while decoding "
                                + _PrimitiveName
                                + ": Expected msc-Number ISDN-AddressString or sgsn-Number ISDN-AddressString or mmeNumber, but found: "
                                + asnInputStream.getTag(), MAPParsingComponentExceptionReason.MistypedParameter);
        }
    }

    public void encodeAll(AsnOutputStream asnOutputStream) throws MAPException {
        this.encodeAll(asnOutputStream, this.getTagClass(), this.getTag());
    }

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

    public void encodeData(AsnOutputStream asnOutputStream) throws MAPException {

        int i1 = 0;
        if (mscNumber != null)
            i1++;
        if (sgsnNumber != null)
            i1++;
        if (mmeNumber != null)
            i1++;

        if (i1 != 1)
            throw new MAPException("Error while encoding the " + _PrimitiveName + ": one of choice must be defined, fiund: "
                    + i1);

        if (this.mscNumber != null) {
            ((ISDNAddressStringImpl) this.mscNumber).encodeData(asnOutputStream);
        } else if (this.sgsnNumber != null) {
            ((ISDNAddressStringImpl) this.sgsnNumber).encodeData(asnOutputStream);
        } else {
            ((DiameterIdentityImpl) this.mmeNumber).encodeData(asnOutputStream);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.mscNumber != null) {
            sb.append(" mscNumber=");
            sb.append(this.mscNumber);
        }
        if (this.sgsnNumber != null) {
            sb.append(" sgsnNumber=");
            sb.append(this.sgsnNumber);
        }
        if (this.mmeNumber != null) {
            sb.append(" mmeNumber=");
            sb.append(this.mmeNumber);
        }

        sb.append("]");

        return sb.toString();
    }
}
