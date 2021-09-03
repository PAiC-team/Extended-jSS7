
package org.restcomm.protocols.ss7.sccp.parameter;

/**
 * Release cause values for connection-oriented message
 */
public enum ReleaseCauseValue {

    // ITU and ANSI
    // in ITU version 0x1 is END_USER_CONGESTION, in ANSI it's END_USER_BUSY
    END_USER_ORIGINATED(0x0), ITU_END_USER_CONGESTION_ANSI_END_USER_BUSY(0x1), END_USER_FAILURE(0x2), SCCP_USER_ORIGINATED(0x3),
    REMOTE_PROCEDURE_ERROR(0x4), INCONSISTENT_CONNECTION_DATA(0x5), ACCESS_FAULURE(0x6), ACCESS_CONGESTION(0x7),
    SUBSYSTEM_FAILURE(0x8), SUBSYSTEM_CONGESTION(0x9), MTP_FAILURE(0xA), NETWORK_CONGESTION(0xB),
    EXPIRATION_OF_RESET_TIMER(0xC), EXPIRATION_OF_RECEIVE_INACTIVITY_TIMER(0xD), UNQUALIFIED(0xF), SCCP_FAILURE(0x10);

    private int code;

    private ReleaseCauseValue(int code) {
        this.code = code;
    }

    public int getValue() {
        return this.code;
    }

    public static ReleaseCauseValue getInstance(int code) {
        switch (code) {
        case 0x0:
            return END_USER_ORIGINATED;
        case 0x1:
            return ITU_END_USER_CONGESTION_ANSI_END_USER_BUSY;
        case 0x2:
            return END_USER_FAILURE;
        case 0x3:
            return SCCP_USER_ORIGINATED;
        case 0x4:
            return REMOTE_PROCEDURE_ERROR;
        case 0x5:
            return INCONSISTENT_CONNECTION_DATA;
        case 0x6:
            return ACCESS_FAULURE;
        case 0x7:
            return ACCESS_CONGESTION;
        case 0x8:
            return SUBSYSTEM_FAILURE;
        case 0x9:
            return SUBSYSTEM_CONGESTION;
        case 0xA:
            return MTP_FAILURE;
        case 0xB:
            return NETWORK_CONGESTION;
        case 0xC:
            return EXPIRATION_OF_RESET_TIMER;
        case 0xD:
            return EXPIRATION_OF_RECEIVE_INACTIVITY_TIMER;
        case 0xF:
            return UNQUALIFIED;
        case 0x10:
            return SCCP_FAILURE;

        default:
            return UNQUALIFIED;
        }
    }

    public boolean isError() {
        switch (this) {
            case END_USER_FAILURE:
            case REMOTE_PROCEDURE_ERROR:
            case ACCESS_FAULURE:
            case SUBSYSTEM_FAILURE:
            case MTP_FAILURE:
            case EXPIRATION_OF_RESET_TIMER:
            case EXPIRATION_OF_RECEIVE_INACTIVITY_TIMER:
            case SCCP_FAILURE:
                return true;
            default:
                return false;
        }
    }
}
