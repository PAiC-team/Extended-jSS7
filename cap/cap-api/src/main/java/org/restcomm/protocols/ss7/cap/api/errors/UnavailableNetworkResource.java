
package org.restcomm.protocols.ss7.cap.api.errors;

/**
 *
 UnavailableNetworkResource ::= ENUMERATED { unavailableResources (0), componentFailure (1), basicCallProcessingException (2),
 * resourceStatusFailure (3), endUserFailure (4) } -- Indicates the network resource that failed.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum UnavailableNetworkResource {

    unavailableResources(0), componentFailure(1), basicCallProcessingException(2), resourceStatusFailure(3), endUserFailure(4);

    private int code;

    private UnavailableNetworkResource(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static UnavailableNetworkResource getInstance(int code) {
        switch (code) {
            case 0:
                return UnavailableNetworkResource.unavailableResources;
            case 1:
                return UnavailableNetworkResource.componentFailure;
            case 2:
                return UnavailableNetworkResource.basicCallProcessingException;
            case 3:
                return UnavailableNetworkResource.resourceStatusFailure;
            case 4:
                return UnavailableNetworkResource.endUserFailure;
            default:
                return null;
        }
    }
}
