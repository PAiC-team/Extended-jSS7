package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

/**
*
<code>
CGEncountered ::= ENUMERATED {
noCGencountered (0),
manualCGencountered (1),
scpOverload (2)
}
-- Indicates the type of automatic call gapping encountered, if any.
</code>
*
*/
public enum CGEncountered {
    noCGencountered(0), manualCGencountered(1), scpOverload(2);

    private int code;

    private CGEncountered(int code) {
        this.code = code;
    }

    public static CGEncountered getInstance(int code) {
        switch (code) {
            case 0:
                return CGEncountered.noCGencountered;
            case 1:
                return CGEncountered.manualCGencountered;
            case 2:
                return CGEncountered.scpOverload;
            default:
                return null;
        }
    }

    public int getCode() {
        return code;
    }

}
