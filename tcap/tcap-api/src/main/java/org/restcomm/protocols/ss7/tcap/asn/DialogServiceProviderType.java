
package org.restcomm.protocols.ss7.tcap.asn;

import org.restcomm.protocols.ss7.tcap.asn.comp.PAbortCauseType;

public enum DialogServiceProviderType {

    Null(0), NoReasonGiven(1), NoCommonDialogPortion(2);

    private long type = -1;

    DialogServiceProviderType(long dialogServiceProviderType) {
        this.type = dialogServiceProviderType;
    }

    /**
     * @return the type
     */
    public long getType() {
        return type;
    }

    public static DialogServiceProviderType getFromInt(long dialogServiceProviderType) throws ParseException {
        if (dialogServiceProviderType == 0) {
            return Null;
        } else if (dialogServiceProviderType == 1) {
            return NoReasonGiven;
        } else if (dialogServiceProviderType == 2) {
            return NoCommonDialogPortion;
        }

        throw new ParseException(PAbortCauseType.IncorrectTxPortion, null, "Wrong value of type: " + dialogServiceProviderType);
    }

}
