
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * @author baranowb
 *
 */
public class ApplicationContextNameImpl implements ApplicationContextName {

    // object identifier value
    private long[] oid;

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#decode(org.mobicents.protocols .asn.AsnInputStream)
     */
    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            AsnInputStream localAis = asnInputStream.readSequenceStream();
            int tag = localAis.readTag();
            if (tag != Tag.OBJECT_IDENTIFIER || localAis.getTagClass() != Tag.CLASS_UNIVERSAL)
                throw new ParseException(PAbortCauseType.IncorrectTxPortion, null,
                        "Error decoding ApplicationContextName: bad tag or tagClass, found tag=" + tag + ", tagClass="
                                + localAis.getTagClass());
            this.oid = localAis.readObjectIdentifier();
        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "IOException while decoding ApplicationContextName: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null,
                    "AsnException while decoding ApplicationContextName: " + e.getMessage(), e);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols .asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        if (this.oid == null)
            throw new EncodeException("Error while decoding ApplicationContextName: No OID value set");
        try {
            asnOutputStream.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _TAG);
            int pos = asnOutputStream.StartContentDefiniteLength();
            asnOutputStream.writeObjectIdentifier(this.oid);
            asnOutputStream.FinalizeContent(pos);
        } catch (IOException e) {
            throw new EncodeException("IOException while encoding ApplicationContextName: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new EncodeException("IOException while encoding ApplicationContextName: " + e.getMessage(), e);
        }
    }

    /**
     * @return the oid
     */
    public long[] getOid() {
        return oid;
    }

    /**
     * @param oid the oid to set
     */
    public void setOid(long[] oid) {
        this.oid = oid;
    }

    public String getStringValue() {
        return Arrays.toString(oid);
    }

    public String toString() {
        return "ApplicationContextName[oid=" + Arrays.toString(oid) + "]";
    }

}
