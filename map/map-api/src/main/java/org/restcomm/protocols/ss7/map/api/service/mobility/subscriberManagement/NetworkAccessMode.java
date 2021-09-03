
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 NetworkAccessMode ::= ENUMERATED { packetAndCircuit (0), onlyCircuit (1), onlyPacket (2), ...} -- if unknown values are
 * received in NetworkAccessMode -- they shall be discarded.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum NetworkAccessMode {
    packetAndCircuit(0), onlyCircuit(1), onlyPacket(2);

    private int code;

    private NetworkAccessMode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static NetworkAccessMode getInstance(int code) {
        switch (code) {
            case 0:
                return NetworkAccessMode.packetAndCircuit;
            case 1:
                return NetworkAccessMode.onlyCircuit;
            case 2:
                return NetworkAccessMode.onlyPacket;
            default:
                return null;
        }
    }
}
