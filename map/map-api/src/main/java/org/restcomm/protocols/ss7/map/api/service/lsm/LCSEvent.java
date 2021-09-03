package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 * LCS-Event ::= ENUMERATED { emergencyCallOrigination (0), emergencyCallRelease (1), mo-lr (2), ..., deferredmt-lrResponse (3)
 * } -- exception handling: -- a SubscriberLocationReport-Arg containing an unrecognized LCS-Event -- shall be rejected by a
 * receiver with a return error cause of unexpected data value
 *
 * @author amit bhayani
 *
 */
public enum LCSEvent {

    emergencyCallOrigination(0), emergencyCallRelease(1), molr(2), deferredmtlrResponse(3);

    private final int event;

    private LCSEvent(int event) {
        this.event = event;
    }

    public int getEvent() {
        return this.event;
    }

    public static LCSEvent getLCSEvent(int event) {
        switch (event) {
            case 0:
                return emergencyCallOrigination;
            case 1:
                return emergencyCallRelease;
            case 2:
                return molr;
            case 3:
                return deferredmtlrResponse;
            default:
                return null;
        }
    }
}
