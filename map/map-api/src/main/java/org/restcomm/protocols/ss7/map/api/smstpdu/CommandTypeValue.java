package org.restcomm.protocols.ss7.map.api.smstpdu;

/**
 *
 00000000 Enquiry relating to previously submitted short message 1 00000001 Cancel Status Report Request relating to
 * previously submitted short message 0 00000010 Delete previously submitted Short Message 0 00000011 Enable Status Report
 * Request relating to previously submitted short message 0 00000100..00011111 Reserved unspecified 11100000..11111111 Values
 * specific for each SC 1 or 0
 *
 * @author sergey vetyutnev
 *
 */
public enum CommandTypeValue {

    EnquiryPreviouslySubmittedShortMessage(0), CancelStatusReportRequest(1), DeletePreviouslySubmittedShortMessage(2),
    EnableStatusReportRequestToPreviouslySubmittedShortMessage(3), Reserved(256);

    private int code;

    private CommandTypeValue(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CommandTypeValue getInstance(int code) {
        switch (code) {
            case 0:
                return EnquiryPreviouslySubmittedShortMessage;
            case 1:
                return CancelStatusReportRequest;
            case 2:
                return DeletePreviouslySubmittedShortMessage;
            case 3:
                return EnableStatusReportRequestToPreviouslySubmittedShortMessage;
            default:
                return Reserved;
        }
    }
}