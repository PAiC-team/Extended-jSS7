
package org.restcomm.protocols.ss7.map.api.primitives;

/**
 *
 AccessNetworkProtocolId ::= ENUMERATED { ts3G-48006 (1), ts3G-25413 (2), ...} -- exception handling: -- For
 * AccessNetworkSignalInfo sequences containing this parameter with any -- other value than the ones listed the receiver shall
 * ignore the whole -- AccessNetworkSignalInfo sequence.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum AccessNetworkProtocolId {

    ts3G_48006(1), ts3G_25413(2);

    private int code;

    private AccessNetworkProtocolId(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static AccessNetworkProtocolId getInstance(int code) {
        switch (code) {
            case 0:
                return AccessNetworkProtocolId.ts3G_48006;
            case 1:
                return AccessNetworkProtocolId.ts3G_25413;
            default:
                return null;
        }
    }
}
