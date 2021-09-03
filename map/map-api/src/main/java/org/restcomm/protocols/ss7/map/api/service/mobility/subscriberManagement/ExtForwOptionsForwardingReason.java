
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 -- bits 43: forwarding reason -- 00 ms not reachable -- 01 ms busy -- 10 no reply -- 11 unconditional
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum ExtForwOptionsForwardingReason {
    msNotReachable(0), msBusy(1), noReply(2), unconditional(3);

    private int code;

    private ExtForwOptionsForwardingReason(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ExtForwOptionsForwardingReason getInstance(int code) {
        switch (code) {
            case 0:
                return ExtForwOptionsForwardingReason.msNotReachable;
            case 1:
                return ExtForwOptionsForwardingReason.msBusy;
            case 2:
                return ExtForwOptionsForwardingReason.noReply;
            case 3:
                return ExtForwOptionsForwardingReason.unconditional;
            default:
                return null;
        }
    }
}
