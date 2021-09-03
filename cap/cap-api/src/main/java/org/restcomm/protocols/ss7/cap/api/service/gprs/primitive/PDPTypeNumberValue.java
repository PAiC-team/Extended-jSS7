package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public enum PDPTypeNumberValue {

    PPP(0x01), IPV4(0x21), IPV6(0x57);

    private int code;

    private PDPTypeNumberValue(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static PDPTypeNumberValue getInstance(int code) {
        switch (code) {
            case 0x01:
                return PDPTypeNumberValue.PPP;
            case 0x21:
                return PDPTypeNumberValue.IPV4;
            case 0x57:
                return PDPTypeNumberValue.IPV6;
            default:
                return null;
        }
    }
}
