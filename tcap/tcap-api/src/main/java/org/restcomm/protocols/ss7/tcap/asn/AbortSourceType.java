
package org.restcomm.protocols.ss7.tcap.asn;

import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * @author baranowb
 *
 */
public enum AbortSourceType {

    User(0), Provider(1);

    private long type = -1;

    AbortSourceType(long t) {
        this.type = t;
    }

    /**
     * @return the type
     */
    public long getType() {
        return type;
    }

    public static AbortSourceType getFromInt(long abortSourceType) throws ParseException {
        if (abortSourceType == 0) {
            return User;
        } else if (abortSourceType == 1) {
            return Provider;
        }

        throw new ParseException(PAbortCauseType.IncorrectTxPortion, null, "Wrong value of type: " + abortSourceType);
    }

}
