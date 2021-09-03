
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

/**
 *
 Used-RAT-Type::= ENUMERATED { utran (0), geran (1), gan (2), i-hspa-evolution (3), e-utran (4), ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum UsedRATType {

    utran(0), geran(1), gan(2), iHspaEvolution(3), eUtran(4);

    private int code;

    private UsedRATType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static UsedRATType getInstance(int code) {
        switch (code) {
            case 0:
                return UsedRATType.utran;
            case 1:
                return UsedRATType.geran;
            case 2:
                return UsedRATType.gan;
            case 3:
                return UsedRATType.iHspaEvolution;
            case 4:
                return UsedRATType.eUtran;
            default:
                return null;
        }
    }
}
