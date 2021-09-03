package org.restcomm.protocols.ss7.sccp.impl.mgmt;

/**
 * @author baranowb
 *
 */
public enum Mtp3UnavailabiltyCauseType {

    CAUSE_UNKNOWN(0), CAUSE_UNEQUIPED(1), CAUSE_INACCESSIBLE(2);

    Mtp3UnavailabiltyCauseType(int x) {
        this.t = x;
    }

    private int t;

    public int getType() {
        return t;
    }

    public static final Mtp3UnavailabiltyCauseType fromInt(int v) {
        switch (v) {
            case 0:
                return CAUSE_UNKNOWN;
            case 1:
                return CAUSE_UNEQUIPED;
            case 2:
                return CAUSE_INACCESSIBLE;

            default:
                return null;

        }
    }
}
