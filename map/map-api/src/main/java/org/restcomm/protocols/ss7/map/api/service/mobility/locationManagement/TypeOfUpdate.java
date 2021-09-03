
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

/**
 *
 TypeOfUpdate ::= ENUMERATED { sgsn-change (0), mme-change (1), ...} -- TypeOfUpdate shall be absent if CancellationType is
 * different from updateProcedure
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum TypeOfUpdate {

    sgsnChange(0), mmeChange(1);

    private int code;

    private TypeOfUpdate(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static TypeOfUpdate getInstance(int code) {
        switch (code) {
            case 0:
                return TypeOfUpdate.sgsnChange;
            case 1:
                return TypeOfUpdate.mmeChange;
            default:
                return null;
        }
    }
}
