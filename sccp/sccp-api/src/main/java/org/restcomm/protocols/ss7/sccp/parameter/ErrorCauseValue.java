
package org.restcomm.protocols.ss7.sccp.parameter;

/**
 * Error cause values for connection-oriented message
 */
public enum ErrorCauseValue {

    // ITU and ANSI
    LRN_MISMATCH_UNASSIGNED_DESTINATION_LRN(0x0), LRN_MISMATCH_INCONSISTENT_SOURCE_LRN(0x1), POINT_CODE_MISMATCH(0x2),
    SERVICE_CLASS_MISMATCH(0x3), UNQUALIFIED(0x4);

    private int code;

    private ErrorCauseValue(int code) {
        this.code = code;
    }

    public int getValue() {
        return this.code;
    }

    public static ErrorCauseValue getInstance(int code) {
        switch (code) {
        case 0x0:
            return LRN_MISMATCH_UNASSIGNED_DESTINATION_LRN;
        case 0x1:
            return LRN_MISMATCH_INCONSISTENT_SOURCE_LRN;
        case 0x2:
            return POINT_CODE_MISMATCH;
        case 0x3:
            return SERVICE_CLASS_MISMATCH;
        case 0x4:
            return UNQUALIFIED;

        default:
            return UNQUALIFIED;
        }
    }
}
