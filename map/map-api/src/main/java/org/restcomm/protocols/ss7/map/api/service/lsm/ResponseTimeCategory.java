package org.restcomm.protocols.ss7.map.api.service.lsm;

/**
 * ResponseTimeCategory ::= ENUMERATED { lowdelay (0), delaytolerant (1), ... } -- exception handling: -- an unrecognized value
 * shall be treated the same as value 1 (delaytolerant)
 *
 * @author amit bhayani
 *
 */
public enum ResponseTimeCategory {

    lowdelay(0), delaytolerant(1);

    private final int category;

    private ResponseTimeCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return this.category;
    }

    public static ResponseTimeCategory getResponseTimeCategory(int category) {
        switch (category) {
            case 0:
                return lowdelay;
            case 1:
                return delaytolerant;
            default:
                return null;
        }
    }
}
