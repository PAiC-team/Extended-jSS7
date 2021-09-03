package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
public enum PDPTypeOrganizationValue {
    ETSI(0x01), IETF(0x02);

    private int code;

    private PDPTypeOrganizationValue(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static PDPTypeOrganizationValue getInstance(int code) {
        switch (code) {
            case 0x01:
                return PDPTypeOrganizationValue.ETSI;
            case 0x02:
                return PDPTypeOrganizationValue.IETF;
            default:
                return null;
        }
    }
}