package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 * LCSClientType ::= ENUMERATED { emergencyServices (0), valueAddedServices (1), plmnOperatorServices (2),
 * lawfulInterceptServices (3), ... } -- exception handling: -- unrecognized values may be ignored if the LCS client uses the
 * privacy override -- otherwise, an unrecognized value shall be treated as unexpected data by a receiver -- a return error
 * shall then be returned if received in a MAP invoke
 *
 * @author amit bhayani
 *
 */
public enum LCSClientType {

    emergencyServices(0), valueAddedServices(1), plmnOperatorServices(2), lawfulInterceptServices(3);

    private final int type;

    private LCSClientType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public static LCSClientType getLCSClientType(int type) {
        switch (type) {
            case 0:
                return emergencyServices;
            case 1:
                return valueAddedServices;
            case 2:
                return plmnOperatorServices;
            case 3:
                return lawfulInterceptServices;
            default:
                return null;
        }
    }
}
