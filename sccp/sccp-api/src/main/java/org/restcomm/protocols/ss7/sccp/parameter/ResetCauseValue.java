
package org.restcomm.protocols.ss7.sccp.parameter;

/**
 * Reset cause values for connection-oriented message
 */
public enum ResetCauseValue {

    // ITU
    END_USER_ORIGINATED(0x0), SCCP_USER_ORIGINATED(0x1), MESSAGE_OUT_OF_ORDER_INCORRECT_PS(0x2),
    MESSAGE_OUT_OF_ORDER_INCORRECT_PR(0x3), REMOTE_PROCEDURE_ERROR_MESSAGE_OUT_OF_WINDOW(0x4),
    REMOTE_PROCEDURE_ERROR_INCORRECT_PS_AFTER_REINITIALIZATION(0x5), REMOTE_PROCEDURE_ERROR_GENERAL(0x6),
    REMOTE_END_USER_OPERATIONAL(0x7), NETWORK_OPERATIONAL(0x8), ACCESS_OPERATIONAL(0x9), NETWORK_CONGESTION(0xA),
    UNQUALIFIED(0xC),

    // ANSI
    NOT_OBTAINABLE(0xB);

    private int code;

    private ResetCauseValue(int code) {
        this.code = code;
    }

    public int getValue() {
        return this.code;
    }

    public static ResetCauseValue getInstance(int code) {
        switch (code) {
        // ITU
        case 0x0:
            return END_USER_ORIGINATED;
        case 0x1:
            return SCCP_USER_ORIGINATED;
        case 0x2:
            return MESSAGE_OUT_OF_ORDER_INCORRECT_PS;
        case 0x3:
            return MESSAGE_OUT_OF_ORDER_INCORRECT_PR;
        case 0x4:
            return REMOTE_PROCEDURE_ERROR_MESSAGE_OUT_OF_WINDOW;
        case 0x5:
            return REMOTE_PROCEDURE_ERROR_INCORRECT_PS_AFTER_REINITIALIZATION;
        case 0x6:
            return REMOTE_PROCEDURE_ERROR_GENERAL;
        case 0x7:
            return REMOTE_END_USER_OPERATIONAL;
        case 0x8:
            return NETWORK_OPERATIONAL;
        case 0x9:
            return ACCESS_OPERATIONAL;
        case 0xA:
            return NETWORK_CONGESTION;
        case 0xC:
            return UNQUALIFIED;

        // ANSI
        case 0xB:
            return NOT_OBTAINABLE;

        default:
            return UNQUALIFIED;
        }
    }

    public boolean isError() {
        switch (this) {
            case MESSAGE_OUT_OF_ORDER_INCORRECT_PS:
            case MESSAGE_OUT_OF_ORDER_INCORRECT_PR:
            case REMOTE_PROCEDURE_ERROR_MESSAGE_OUT_OF_WINDOW:
            case REMOTE_PROCEDURE_ERROR_INCORRECT_PS_AFTER_REINITIALIZATION:
            case REMOTE_PROCEDURE_ERROR_GENERAL:
                return true;
            default:
                return false;
        }
    }
}
