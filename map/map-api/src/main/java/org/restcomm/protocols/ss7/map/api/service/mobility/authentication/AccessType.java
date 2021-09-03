
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

/**
 *
 AccessType ::= ENUMERATED { call (0), emergencyCall (1), locationUpdating (2), supplementaryService (3), shortMessage (4),
 * gprsAttach (5), routingAreaUpdating (6), serviceRequest (7), pdpContextActivation (8), pdpContextDeactivation (9), ...,
 * gprsDetach (10)} -- exception handling: -- received values greater than 10 shall be ignored.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum AccessType {

    call(0), emergencyCall(1), locationUpdating(2), supplementaryService(3), shortMessage(4),
    gprsAttach(5), routingAreaUpdating(6), serviceRequest(7), pdpContextActivation(8),
    pdpContextDeactivation(9), gprsDetach(10);

    private int code;

    private AccessType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static AccessType getInstance(int code) {
        switch (code) {
            case 0:
                return AccessType.call;
            case 1:
                return AccessType.emergencyCall;
            case 2:
                return AccessType.locationUpdating;
            case 3:
                return AccessType.supplementaryService;
            case 4:
                return AccessType.shortMessage;
            case 5:
                return AccessType.gprsAttach;
            case 6:
                return AccessType.routingAreaUpdating;
            case 7:
                return AccessType.serviceRequest;
            case 8:
                return AccessType.pdpContextActivation;
            case 9:
                return AccessType.pdpContextDeactivation;
            case 10:
                return AccessType.gprsDetach;
            default:
                return null;
        }
    }
}
