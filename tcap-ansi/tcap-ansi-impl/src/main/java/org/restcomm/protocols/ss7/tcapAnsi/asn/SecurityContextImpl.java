
package org.restcomm.protocols.ss7.tcapAnsi.asn;

import java.io.IOException;
import java.util.Arrays;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.EncodeException;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.ParseException;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.SecurityContext;
import org.restcomm.protocols.ss7.tcapAnsi.api.asn.comp.PAbortCause;

/**
*
* @author sergey vetyutnev
*
*/
public class SecurityContextImpl implements SecurityContext {

    private Long integerSecurityId;
    private long[] objectSecurityId;

    @Override
    public Long getIntegerSecurityId() {
        return integerSecurityId;
    }

    @Override
    public void setIntegerSecurityId(Long val) {
        integerSecurityId = val;
        objectSecurityId = null;
    }

    @Override
    public long[] getObjectSecurityId() {
        return objectSecurityId;
    }

    @Override
    public void setObjectSecurityId(long[] val) {
        integerSecurityId = null;
        objectSecurityId = val;
    }

    @Override
    public void decode(AsnInputStream ais) throws ParseException {
        integerSecurityId = null;
        objectSecurityId = null;

        try {
            if (!ais.isTagPrimitive() || ais.getTagClass() != Tag.CLASS_CONTEXT_SPECIFIC)
                throw new ParseException(PAbortCause.BadlyStructuredDialoguePortion,
                        "Error decoding SecurityContext: bad tagClass or not primitive, found tagClass=" + ais.getTagClass());

            switch (ais.getTag()) {
            case SecurityContext._TAG_SECURITY_CONTEXT_OID:
                this.objectSecurityId = ais.readObjectIdentifier();
                break;

            case SecurityContext._TAG_SECURITY_CONTEXT_INTEGER:
                this.integerSecurityId = ais.readInteger();
                break;

            default:
                throw new ParseException(PAbortCause.BadlyStructuredDialoguePortion,
                        "Error decoding SecurityContext: bad tag, found tag=" + ais.getTag());
            }

        } catch (IOException e) {
            throw new ParseException(PAbortCause.BadlyStructuredDialoguePortion,
                    "IOException while decoding SecurityContext: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new ParseException(PAbortCause.BadlyStructuredDialoguePortion,
                    "AsnException while decoding SecurityContext: " + e.getMessage(), e);
        }
    }

    @Override
    public void encode(AsnOutputStream aos) throws EncodeException {

        try {
            if (this.integerSecurityId != null) {
                aos.writeInteger(Tag.CLASS_CONTEXT_SPECIFIC, SecurityContext._TAG_SECURITY_CONTEXT_INTEGER, integerSecurityId);
            } else if (this.objectSecurityId != null) {
                aos.writeObjectIdentifier(Tag.CLASS_CONTEXT_SPECIFIC, SecurityContext._TAG_SECURITY_CONTEXT_OID, this.objectSecurityId);
            } else {
                throw new EncodeException("Error while encoding SecurityContext: Neither integerSecurityId nor objectSecurityId value is set");
            }

        } catch (IOException e) {
            throw new EncodeException("IOException while encoding SecurityContext: " + e.getMessage(), e);
        } catch (AsnException e) {
            throw new EncodeException("AsnException while encoding SecurityContext: " + e.getMessage(), e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SecurityContext[");
        if (this.integerSecurityId != null) {
            sb.append("integerSecurityId=");
            sb.append(this.integerSecurityId);
        } else if (this.objectSecurityId != null) {
            sb.append("objectSecurityId=");
            sb.append(Arrays.toString(objectSecurityId));
        }
        sb.append("]");
        return sb.toString();
    }

}
