
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.BitSetStrictLength;
import org.mobicents.protocols.asn.External;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * <p>
 * According to ITU-T Rec Q.773 the UserInformation is defined as
 * </p>
 * <br/>
 * <p>
 * user-information [30] IMPLICIT SEQUENCE OF EXTERNAL
 * </p>
 * <br/>
 * <p>
 * For definition of EXTERNAL look {@link org.mobicents.protocols.asn.External} from Mobicents ASN module
 * </p>
 *
 * @author baranowb
 * @author amit bhayani
 *
 */
public class UserInformationImpl implements UserInformation {

    private External ext = new External();

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.asn.External#decode(org.mobicents.protocols.asn.AsnInputStream)
     */

    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            AsnInputStream localAis = asnInputStream.readSequenceStream();

            int tag = localAis.readTag();
            if (tag != Tag.EXTERNAL || localAis.getTagClass() != Tag.CLASS_UNIVERSAL)
                throw new AsnException("Error decoding UserInformation.sequence: wrong tag or tag class: tag=" + tag
                        + ", tagClass=" + localAis.getTagClass());
            ext.decode(localAis);
        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "IOException when decoding UserInformation: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "AsnException when decoding UserInformation: " + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.asn.External#encode(org.mobicents.protocols.asn.AsnOutputStream)
     */

    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG);
            int pos = asnOutputStream.StartContentDefiniteLength();

            ext.encode(asnOutputStream);

            asnOutputStream.FinalizeContent(pos);

        } catch (AsnException e) {
            throw new EncodeException("AsnException when encoding UserInformation: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] getEncodeType() throws AsnException {
        return ext.getEncodeType();
    }

    @Override
    public void setEncodeType(byte[] data) {
        ext.setEncodeType(data);
    }

    @Override
    public BitSetStrictLength getEncodeBitStringType() throws AsnException {
        return ext.getEncodeBitStringType();
    }

    @Override
    public void setEncodeBitStringType(BitSetStrictLength bitSetStrictLength) {
        this.ext.setEncodeBitStringType(bitSetStrictLength);
    }

    @Override
    public boolean isOid() {
        return this.ext.isOid();
    }

    @Override
    public void setOid(boolean oid) {
        this.ext.setOid(oid);
    }

    @Override
    public boolean isInteger() {
        return this.ext.isInteger();
    }

    @Override
    public void setInteger(boolean isInteger) {
        this.ext.setInteger(isInteger);
    }

    @Override
    public boolean isObjDescriptor() {
        return this.ext.isObjDescriptor();
    }

    @Override
    public void setObjDescriptor(boolean objDescriptor) {
        this.ext.setObjDescriptor(objDescriptor);
    }

    @Override
    public long[] getOidValue() {
        return this.ext.getOidValue();
    }

    @Override
    public void setOidValue(long[] oidValue) {
        this.ext.setOidValue(oidValue);
    }

    @Override
    public long getIndirectReference() {
        return this.ext.getIndirectReference();
    }

    @Override
    public void setIndirectReference(long indirectReference) {
        this.ext.setIndirectReference(indirectReference);
    }

    @Override
    public String getObjDescriptorValue() {
        return this.ext.getObjDescriptorValue();
    }

    @Override
    public void setObjDescriptorValue(String objDescriptorValue) {
        this.ext.setObjDescriptorValue(objDescriptorValue);
    }

    @Override
    public boolean isAsn() {
        return this.ext.isAsn();
    }

    @Override
    public void setAsn(boolean asn) {
        this.ext.setAsn(asn);
    }

    @Override
    public boolean isOctet() {
        return this.ext.isOctet();
    }

    @Override
    public void setOctet(boolean octet) {
        this.ext.setOctet(octet);
    }

    @Override
    public boolean isArbitrary() {
        return this.ext.isArbitrary();
    }

    @Override
    public void setArbitrary(boolean arbitrary) {
        this.ext.setArbitrary(arbitrary);
    }
}
