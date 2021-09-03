package org.restcomm.protocols.ss7.mtp;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class Mtp3StatusPrimitive extends Mtp3Primitive {

    private Mtp3StatusCause cause;
    /**
     * Dialogic: Congestion status (if status = 0x02).<br/>
     * This field is set to the current congestion level in the range 0 to 3, <br/>
     * where 0 means no congestion and 3 means maximum congestion. <br/>
     * Many networks use only a single level of congestion (that is, 1). <br/>
     */
    private int congestionLevel;
    /**
     * The User Part identity (3==SCCP, 4==TUP, 5==ISUP, 6==DUP, 8==MTP Testing User Part, 0==unknown)
     */
    private int userPartIdentity;

    public Mtp3StatusPrimitive(int affectedDpc, Mtp3StatusCause cause, int congestionLevel, int userPartIdentity) {
        super(STATUS, affectedDpc);
        this.cause = cause;
        this.congestionLevel = congestionLevel;
        this.userPartIdentity = userPartIdentity;
    }

    public Mtp3StatusCause getCause() {
        return this.cause;
    }

    public int getCongestionLevel() {
        return this.congestionLevel;
    }

    public int getUserPartIdentity() {
        return this.userPartIdentity;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("MTP-STATUS: AffectedDpc=");
        sb.append(this.affectedDpc);
        sb.append(", Cause=");
        sb.append(this.cause.toString());
        sb.append(", CongLevel=");
        sb.append(this.congestionLevel);
        sb.append(", userPartIdentity=");
        sb.append(this.userPartIdentity);

        return sb.toString();
    }
}
