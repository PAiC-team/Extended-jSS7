
package org.restcomm.protocols.ss7.tcap.asn;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * @author baranowb
 *
 */
public class AbortSourceImpl implements AbortSource {

    private AbortSourceType type;

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.AbortSource#getAbortSourceType()
     */
    public AbortSourceType getAbortSourceType() {
        return this.type;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.restcomm.protocols.ss7.tcap.asn.AbortSource#setAbortSourceType(org.restcomm.protocols.ss7.tcap.asn.AbortSourceType)
     */
    public void setAbortSourceType(AbortSourceType t) {
        this.type = t;

    }

    public String toString() {
        return "AbortSource[type=" + type + "]";
    }

    public void decode(AsnInputStream asnInputStream) throws ParseException {
        try {
            long t = asnInputStream.readInteger();
            this.type = AbortSourceType.getFromInt(t);
        } catch (IOException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null, "IOException while decoding AbortSource: "
                    + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCauseType.BadlyFormattedTxPortion, null, "AsnException while decoding AbortSource: "
                    + e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.tcap.asn.Encodable#encode(org.mobicents.protocols.asn.AsnOutputStream)
     */
    public void encode(AsnOutputStream asnOutputStream) throws EncodeException {
        if (type == null)
            throw new EncodeException("Error encoding AbortSource: No type set");
        try {
            asnOutputStream.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, _TAG, type.getType());
        } catch (IOException e) {
            throw new EncodeException("IOException while encoding AbortSource: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new EncodeException("AsnException while encoding AbortSource: " + e.getMessage(), e);
        }
    }

}
