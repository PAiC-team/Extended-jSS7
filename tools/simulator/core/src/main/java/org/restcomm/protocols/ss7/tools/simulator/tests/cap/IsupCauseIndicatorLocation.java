
package org.restcomm.protocols.ss7.tools.simulator.tests.cap;

/**
 *
 * IsupCauseIndicatorLocation ::= ENUMERATED { user(0),
 * privateNetworkServingLocalUser(1), publicNetworkServingLocalUser(2),
 * transitNetwork(3), publicNetworkServingRemoteUser(4),
 * privateNetworkServingRemoteUser(5), internationalNetwork(7) }
 *
 * @author mnowa
 *
 * @see Q.850
 */
public enum IsupCauseIndicatorLocation {
    user(0), privateNetworkServingLocalUser(1), publicNetworkServingLocalUser(2), transitNetwork(
            3), publicNetworkServingRemoteUser(4), privateNetworkServingRemoteUser(5), internationalNetwork(7),
    networkBeyondIp(10);

    private int code;

    private IsupCauseIndicatorLocation(int code) {
        this.code = code;
    }

    public static IsupCauseIndicatorLocation getInstance(int code) {
        switch (code) {
            case 0:
                return user;
            case 1:
                return privateNetworkServingLocalUser;
            case 2:
                return publicNetworkServingLocalUser;
            case 3:
                return transitNetwork;
            case 4:
                return publicNetworkServingRemoteUser;
            case 5:
                return privateNetworkServingRemoteUser;
            case 7:
                return internationalNetwork;
            case 10:
                return networkBeyondIp;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
