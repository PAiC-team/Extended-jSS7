
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
 ForwardingReason ::= ENUMERATED { notReachable (0), busy (1), noReply (2)}
 *
 * -- bits 43: forwarding reason -- 00 ms not reachable -- 01 ms busy -- 10 no reply -- 11 unconditional when used in a SRI
 * Result, -- or call deflection when used in a RCH Argument
 *
 *
 * @author cristian veliscu
 *
 */
public enum ForwardingReason {

    notReachable(0), busy(1), noReply(2), unconditionalOrCallDeflection(3);

    private int code;

    private ForwardingReason(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ForwardingReason getForwardingReason(int code) {
        switch (code) {
            case 0:
                return notReachable;
            case 1:
                return busy;
            case 2:
                return noReply;
            case 3:
                return unconditionalOrCallDeflection;
            default:
                return null;
        }
    }
}