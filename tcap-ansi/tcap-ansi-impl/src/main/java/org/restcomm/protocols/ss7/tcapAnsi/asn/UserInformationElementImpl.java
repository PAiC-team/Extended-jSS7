
package org.restcomm.protocols.ss7.tcapAnsi.asn;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.BitSetStrictLength;
import org.mobicents.protocols.asn.External;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.EncodeException;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ParseException;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.UserInformationElement;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.PAbortCause;

/**
*
* @author baranowb
* @author amit bhayani
*
*/
public class UserInformationElementImpl implements UserInformationElement {

    private External ext = new External();


    /*
     * (non-Javadoc)
     *
     * @see org.mobicents.protocols.asn.External#decode(org.mobicents.protocols.asn.AsnInputStream)
     */

    public void decode(AsnInputStream ais) throws ParseException {

        try {
            ext.decode(ais);
        } catch (AsnException e) {
            throw new ParseException(PAbortCause.BadlyStructuredDialoguePortion, "AsnException while decoding UserInformationElement: " + e.getMessage(), e);
        }
    }

    public void encode(AsnOutputStream aos) throws EncodeException {

        try {
            ext.encode(aos);
        } catch (AsnException e) {
            throw new EncodeException("AsnException when encoding UserInformationElement: " + e.getMessage(), e);
        }
    }

    public void encode(AsnOutputStream aos, int tagClass, int tag) throws EncodeException {

        try {
            ext.encode(aos, tagClass, tag);
        } catch (AsnException e) {
            throw new EncodeException("AsnException when encoding UserInformationElement: " + e.getMessage(), e);
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
    public void setEncodeBitStringType(BitSetStrictLength data) {
        ext.setEncodeBitStringType(data);
    }

    @Override
    public boolean isOid() {
        return ext.isOid();
    }

    @Override
    public void setOid(boolean oid) {
        ext.setOid(oid);
    }

    @Override
    public boolean isInteger() {
        return ext.isInteger();
    }

    @Override
    public void setInteger(boolean integer) {
        ext.setInteger(integer);
    }

    @Override
    public boolean isObjDescriptor() {
        return ext.isObjDescriptor();
    }

    @Override
    public void setObjDescriptor(boolean objDescriptor) {
        ext.setObjDescriptor(objDescriptor);
    }

    @Override
    public long[] getOidValue() {
        return ext.getOidValue();
    }

    @Override
    public void setOidValue(long[] oidValue) {
        ext.setOidValue(oidValue);
    }

    @Override
    public long getIndirectReference() {
        return ext.getIndirectReference();
    }

    @Override
    public void setIndirectReference(long indirectReference) {
        ext.setIndirectReference(indirectReference);
    }

    @Override
    public String getObjDescriptorValue() {
        return ext.getObjDescriptorValue();
    }

    @Override
    public void setObjDescriptorValue(String objDescriptorValue) {
        ext.setObjDescriptorValue(objDescriptorValue);
    }

    @Override
    public boolean isAsn() {
        return ext.isAsn();
    }

    @Override
    public void setAsn(boolean asn) {
        ext.setAsn(asn);
    }

    @Override
    public boolean isOctet() {
        return ext.isOctet();
    }

    @Override
    public void setOctet(boolean octet) {
        ext.setOctet(octet);
    }

    @Override
    public boolean isArbitrary() {
        return ext.isArbitrary();
    }

    @Override
    public void setArbitrary(boolean arbitrary) {
        ext.setArbitrary(arbitrary);
    }

}
