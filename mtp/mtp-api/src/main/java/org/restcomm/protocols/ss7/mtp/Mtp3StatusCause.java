package org.restcomm.protocols.ss7.mtp;

/**
 * @author sergey vetyutnev
 *
 */
public enum Mtp3StatusCause {

    SignallingNetworkCongested(0), UserPartUnavailability_Unknown(1), UserPartUnavailability_UnequippedRemoteUser(2), UserPartUnavailability_InaccessibleRemoteUser(
            3);

    private int code;

    private Mtp3StatusCause(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static Mtp3StatusCause getMtp3StatusCause(int cause) {
        switch (cause) {
            case 0:
                return SignallingNetworkCongested;
            case 1:
                return UserPartUnavailability_Unknown;
            case 2:
                return UserPartUnavailability_UnequippedRemoteUser;
            case 3:
                return UserPartUnavailability_InaccessibleRemoteUser;
            default:
                return null;
        }
    }
}
