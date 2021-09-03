
package org.restcomm.protocols.ss7.tcap.asn;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.BitSetStrictLength;
import org.mobicents.protocols.asn.Tag;

/**
 *
 * @author baranowb
 * @author amit bhayani
 *
 */
public interface DialogPortion extends Encodable {

    // Dialog portion is actually type of EXTERNAL, this sucks....
    int _TAG_CLASS = Tag.CLASS_APPLICATION;
    boolean _TAG_PC_PRIMITIVE = false;
    int _TAG = 0x0B;

    /**
     * @return the dialogAPDU
     */
    DialogAPDU getDialogAPDU();

    /**
     * @param dialogAPDU the dialogAPDU to set
     */
    void setDialogAPDU(DialogAPDU dialogAPDU);

    void setUnidirectional(boolean flagUnidirectional);

    boolean isUnidirectional();

    // From External

    boolean isOid();

    void setOid(boolean oid);

    long[] getOidValue();

    void setOidValue(long[] oidValue);

    boolean isInteger();

    void setInteger(boolean integer);

    long getIndirectReference();

    void setIndirectReference(long indirectReference);

    boolean isObjDescriptor();

    void setObjDescriptor(boolean objDescriptor);

    boolean isAsn();

    void setAsn(boolean asn);

    byte[] getEncodeType() throws AsnException;

    void setEncodeType(byte[] data);

    boolean isOctet();

    void setOctet(boolean octet);

    boolean isArbitrary();

    void setArbitrary(boolean arbitrary);

    BitSetStrictLength getEncodeBitStringType() throws AsnException;

    void setEncodeBitStringType(BitSetStrictLength encodeBitStringType);

}
