package org.restcomm.protocols.ss7.sccp.impl.mgmt;

/**
 * @author baranowb
 *
 */
public enum Mtp3CongestionType {

    NULL(-1);

    Mtp3CongestionType(int x) {
        this.t = x;
    }

    private int t;

    public int getType() {
        return t;
    }

    public static final Mtp3CongestionType fromInt(int v) {
        return NULL;
    }
}
