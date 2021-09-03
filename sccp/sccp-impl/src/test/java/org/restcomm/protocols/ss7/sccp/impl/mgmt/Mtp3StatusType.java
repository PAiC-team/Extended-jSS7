package org.restcomm.protocols.ss7.sccp.impl.mgmt;

/**
 * @author baranowb
 *
 */
public enum Mtp3StatusType {

    RemoteUserUnavailable(1), SignalingNetworkCongestion(2);

    Mtp3StatusType(int x) {
        this.t = x;
    }

    private int t;

    public int getType() {
        return t;
    }

    public static final Mtp3StatusType fromInt(int v) {
        switch (v) {
            case 1:
                return RemoteUserUnavailable;
            case 2:
                return SignalingNetworkCongestion;

            default:
                return null;

        }
    }
}
