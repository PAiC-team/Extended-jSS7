
package org.restcomm.protocols.ss7.mtp;

/**
 *
 * @author sergey vetyutnev
 *
 */
public class Mtp3EndCongestionPrimitive extends Mtp3Primitive {

    public Mtp3EndCongestionPrimitive(int affectedDpc) {
        super(END_CONGESTION, affectedDpc);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MTP-END-CONGESTION: AffectedDpc=");
        sb.append(this.affectedDpc);

        return sb.toString();
    }

}
