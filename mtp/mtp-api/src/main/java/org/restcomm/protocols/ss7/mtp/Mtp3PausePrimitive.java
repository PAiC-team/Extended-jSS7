package org.restcomm.protocols.ss7.mtp;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class Mtp3PausePrimitive extends Mtp3Primitive {

    /**
     * @param type
     * @param affectedDpc
     */
    public Mtp3PausePrimitive(int affectedDpc) {
        super(PAUSE, affectedDpc);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("MTP-PAUSE: AffectedDpc=");
        sb.append(this.affectedDpc);

        return sb.toString();
    }
}
