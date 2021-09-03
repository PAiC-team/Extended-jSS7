
package org.restcomm.protocols.ss7.tcap.asn;

import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

/**
 * @author baranowb
 *
 */
public enum ResultType {
    // it iss encoded as INT

    Accepted(0), RejectedPermanent(1);

    private long type = -1;

    ResultType(long t) {
        this.type = t;
    }

    /**
     * @return the type
     */
    public long getType() {
        return type;
    }

    public static ResultType getFromInt(long resultType) throws ParseException {
        if (resultType == 0) {
            return Accepted;
        } else if (resultType == 1) {
            return RejectedPermanent;
        }

        throw new ParseException(PAbortCauseType.IncorrectTxPortion, null, "Wrong value of response: " + resultType);
    }
}
