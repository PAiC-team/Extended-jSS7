
package org.restcomm.protocols.ss7.tcap.asn;

import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

public enum DialogServiceUserType {

    Null(0), NoReasonGive(1), AcnNotSupported(2);

    private long type = -1;

    DialogServiceUserType(long dialogServiceUserType) {
        this.type = dialogServiceUserType;
    }

    /**
     * @return the type
     */
    public long getType() {
        return type;
    }

    public static DialogServiceUserType getFromInt(long dialogServiceUserType) throws ParseException {
        if (dialogServiceUserType == 0) {
            return Null;
        } else if (dialogServiceUserType == 1) {
            return NoReasonGive;
        } else if (dialogServiceUserType == 2) {
            return AcnNotSupported;
        }

        throw new ParseException(PAbortCauseType.IncorrectTxPortion, null, "Wrong value of type: " + dialogServiceUserType);
    }

}
