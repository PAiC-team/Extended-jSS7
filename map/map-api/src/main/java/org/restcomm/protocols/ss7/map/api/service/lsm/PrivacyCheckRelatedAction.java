package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 * PrivacyCheckRelatedAction ::= ENUMERATED { allowedWithoutNotification (0), allowedWithNotification (1), allowedIfNoResponse
 * (2), restrictedIfNoResponse (3), notAllowed (4), ...} -- exception handling: -- a ProvideSubscriberLocation-Arg containing an
 * unrecognized PrivacyCheckRelatedAction -- shall be rejected by the receiver with a return error cause of unexpected data
 * value
 *
 * @author amit bhayani
 *
 */
public enum PrivacyCheckRelatedAction {

    allowedWithoutNotification(0), allowedWithNotification(1), allowedIfNoResponse(2), restrictedIfNoResponse(3), notAllowed(4);

    private final int action;

    private PrivacyCheckRelatedAction(int action) {
        this.action = action;
    }

    public int getAction() {
        return this.action;
    }

    public static PrivacyCheckRelatedAction getPrivacyCheckRelatedAction(int action) {
        switch (action) {
            case 0:
                return allowedWithoutNotification;
            case 1:
                return allowedWithNotification;
            case 2:
                return allowedIfNoResponse;
            case 3:
                return restrictedIfNoResponse;
            case 4:
                return notAllowed;
            default:
                return null;
        }
    }
}
