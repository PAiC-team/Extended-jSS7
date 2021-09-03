
package org.restcomm.protocols.ss7.map.api.service.mobility.imei;

/**
 *
 * EquipmentStatus ::= ENUMERATED { whiteListed (0), blackListed (1), greyListed (2) }
 *
 * @author normandes
 *
 */
public enum EquipmentStatus {

    whiteListed(0), blackListed(1), greyListed(2);

    private int code;

    private EquipmentStatus(int code) {
        this.code = code;
    }

    public static EquipmentStatus getInstance(int code) {
        switch (code) {
            case 0:
                return whiteListed;
            case 1:
                return blackListed;
            case 2:
                return greyListed;
            default:
                return null;
        }
    }

    public int getCode() {
        return code;
    }
}
