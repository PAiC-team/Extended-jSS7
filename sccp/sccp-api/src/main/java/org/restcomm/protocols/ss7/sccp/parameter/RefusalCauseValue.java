
package org.restcomm.protocols.ss7.sccp.parameter;

/**
 * Refusal cause values for connection-oriented message
 */
public enum RefusalCauseValue {

    // ITU and ANSI
    END_USER_ORIGINATED(0x0), END_USER_CONGESTION(0x1), END_USER_FAILURE(0x2), SCCP_USER_ORIGINATED(0x3),
    DESTINATION_ADDRESS_UNKNOWN(0x4), DESTINATION_INACCESSIBLE(0x5), NETWORK_RESOURCE_QOS_NOT_AVAILABLE_NON_TRANSIENT(0x6),
    NETWORK_RESOURCE_QOS_NOT_AVAILABLE_TRANSIENT(0x7), ACCESS_FAILURE(0x8), ACCESS_CONGESTION(0x9), SUBSYSTEM_FAILURE(0xA),
    SUBSYSTEM_CONGESTION(0xB), EXPIRATION_OF_THE_CONNECTION_ESTABLISHMENT_TIMER(0xC), INCOMPATIBLE_USER_DATA(0xD),
    UNQUALIFIED(0xF), HOP_COUNTER_VIOLATION(0x10), SCCP_FAILURE(0x11), NO_TRANSLATION_FOR_AN_ADDRESS_OF_SUCH_NATURE(0x12),
    UNEQUIPPED_USER(0x13);

    private int code;

    private RefusalCauseValue(int code) {
        this.code = code;
    }

    public int getValue() {
        return this.code;
    }

    public static RefusalCauseValue getInstance(int code) {
        switch (code) {
        case 0x0:
            return END_USER_ORIGINATED;
        case 0x1:
            return END_USER_CONGESTION;
        case 0x2:
            return END_USER_FAILURE;
        case 0x3:
            return SCCP_USER_ORIGINATED;
        case 0x4:
            return DESTINATION_ADDRESS_UNKNOWN;
        case 0x5:
            return DESTINATION_INACCESSIBLE;
        case 0x6:
            return NETWORK_RESOURCE_QOS_NOT_AVAILABLE_NON_TRANSIENT;
        case 0x7:
            return NETWORK_RESOURCE_QOS_NOT_AVAILABLE_TRANSIENT;
        case 0x8:
            return ACCESS_FAILURE;
        case 0x9:
            return ACCESS_CONGESTION;
        case 0xA:
            return SUBSYSTEM_FAILURE;
        case 0xB:
            return SUBSYSTEM_CONGESTION;
        case 0xC:
            return EXPIRATION_OF_THE_CONNECTION_ESTABLISHMENT_TIMER;
        case 0xD:
            return INCOMPATIBLE_USER_DATA;
        case 0xF:
            return UNQUALIFIED;
        case 0x10:
            return HOP_COUNTER_VIOLATION;
        case 0x11:
            return SCCP_FAILURE;
        case 0x12:
            return NO_TRANSLATION_FOR_AN_ADDRESS_OF_SUCH_NATURE;
        case 0x13:
            return UNEQUIPPED_USER;

        default:
            return UNQUALIFIED;
        }
    }
}
