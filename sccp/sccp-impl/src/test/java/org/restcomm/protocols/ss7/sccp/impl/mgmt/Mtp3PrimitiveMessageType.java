package org.restcomm.protocols.ss7.sccp.impl.mgmt;

/**
 * @author baranowb
 *
 */
public enum Mtp3PrimitiveMessageType {

    MTP3_PAUSE(3), MTP3_RESUME(4), MTP3_STATUS(5);

    Mtp3PrimitiveMessageType(int x) {
        this.t = x;
    }

    private int t;

    public int getType() {
        return t;
    }

    public static final Mtp3PrimitiveMessageType fromInt(int v) {
        switch (v) {
            case 3:
                return MTP3_PAUSE;
            case 4:
                return MTP3_RESUME;
            case 5:
                return MTP3_STATUS;

            default:
                return null;

        }
    }
}
