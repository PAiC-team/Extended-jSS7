package org.restcomm.protocols.ss7.sccp.impl.mgmt;

/**
 * @author baranowb
 *
 */
public enum SccpMgmtMessageType {
    SSA(1), SSP(2), SST(3), SOR(4), SOG(5), SSC(6);
    SccpMgmtMessageType(int x) {
        this.t = x;
    }

    private int t;

    public int getType() {
        return t;
    }

    public static final SccpMgmtMessageType fromInt(int v) {
        switch (v) {
            case 1:
                return SSA;
            case 2:
                return SSP;
            case 3:
                return SST;
            case 4:
                return SOR;
            case 5:
                return SOG;
            case 6:
                return SSC;
            default:
                return null;

        }
    }
}
