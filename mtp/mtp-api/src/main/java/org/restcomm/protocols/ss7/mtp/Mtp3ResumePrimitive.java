package org.restcomm.protocols.ss7.mtp;

/**
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public class Mtp3ResumePrimitive extends Mtp3Primitive {

    /**
     * @param type
     * @param affectedDpc
     */
    public Mtp3ResumePrimitive(int affectedDpc) {
        super(RESUME, affectedDpc);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("MTP-RESUME: AffectedDpc=");
        sb.append(this.affectedDpc);

        return sb.toString();
    }

}
