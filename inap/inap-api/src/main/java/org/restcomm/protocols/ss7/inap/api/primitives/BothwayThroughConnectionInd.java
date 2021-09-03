package org.restcomm.protocols.ss7.inap.api.primitives;

/**
 *
<code>
BothwayThroughConnectionInd ::= ENUMERATED { bothwayPathRequired(0), bothwayPathNotRequired(1)}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum BothwayThroughConnectionInd {
    bothwayPathRequired(0), bothwayPathNotRequired(1);

    private int code;

    private BothwayThroughConnectionInd(int code) {
        this.code = code;
    }

    public static BothwayThroughConnectionInd getInstance(int code) {
        switch (code) {
            case 0:
                return BothwayThroughConnectionInd.bothwayPathRequired;
            case 1:
                return BothwayThroughConnectionInd.bothwayPathNotRequired;
        }

        return null;
    }

    public int getCode() {
        return code;
    }
}
