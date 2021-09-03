
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

/**
 *
 ModificationInstruction ::= ENUMERATED { deactivate (0), activate (1)}
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum ModificationInstruction {

    deactivate(0), activate(1);

    private int code;

    private ModificationInstruction(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ModificationInstruction getInstance(int code) {
        switch (code) {
            case 0:
                return ModificationInstruction.deactivate;
            case 1:
                return ModificationInstruction.activate;
            default:
                return null;
        }
    }
}
