package org.restcomm.protocols.ss7.map.api.smstpdu;

/**
 *
 * 0 The SMS-STATUS-REPORT is the result of a SMS-SUBMIT. 1 The SMS-STATUS-REPORT is the result of an SMS-COMMAND e.g. an
 * Enquiry.
 *
 * @author sergey vetyutnev
 *
 */
public enum StatusReportQualifier {

    SmsSubmitResult(0), SmsCommandResult(1);

    private int code;

    private StatusReportQualifier(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static StatusReportQualifier getInstance(int code) {
        switch (code) {
            case 0:
                return SmsSubmitResult;
            default:
                return SmsCommandResult;
        }
    }
}