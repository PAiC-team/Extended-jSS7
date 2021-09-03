package org.restcomm.protocols.ss7.map.api.errors;

/**
 *
 * UnknownSubscriberDiagnostic ::= ENUMERATED { imsiUnknown (0), gprsSubscriptionUnknown (1), ..., npdbMismatch (2)} -- if
 * unknown values are received in -- UnknownSubscriberDiagnostic they shall be discarded
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum UnknownSubscriberDiagnostic {

    imsiUnknown(0), gprsSubscriptionUnknown(1), npdbMismatch(2);

    private int code;

    private UnknownSubscriberDiagnostic(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UnknownSubscriberDiagnostic getInstance(int code) {
        switch (code) {
            case 0:
                return imsiUnknown;
            case 1:
                return gprsSubscriptionUnknown;
            case 2:
                return npdbMismatch;
            default:
                return null;
        }
    }

}
