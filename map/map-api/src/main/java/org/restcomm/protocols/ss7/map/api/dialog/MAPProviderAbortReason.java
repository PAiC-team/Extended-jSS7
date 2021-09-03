package org.restcomm.protocols.ss7.map.api.dialog;

/**
 * MAP-ProviderAbortReason ::= ENUMERATED { abnormalDialogue (0), invalidPDU (1)}
 *
 * @author amit bhayani
 *
 */
public enum MAPProviderAbortReason {

    abnormalDialogue(0), invalidPDU(1), unknown;

    private int code;

    private MAPProviderAbortReason(){
    }

    private MAPProviderAbortReason(int code) {
        this.code = code;
    }

    public static MAPProviderAbortReason getInstance(int code) {
        switch (code) {
            case 0:
                return abnormalDialogue;
            case 1:
                return invalidPDU;
            default:
                unknown.code = code;
                return unknown;
        }
    }

    public int getCode() {
        return code;
    }

}
