package org.restcomm.protocols.ss7.inap.api.primitives;

/**
 *
<code>
MiscCallInfo ::= SEQUENCE {
  dpAssignment [1] ENUMERATED {individualLine(0), groupBased(1), officeBased(2)
} OPTIONAL
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum MiscCallInfoDpAssignment {
    individualLine(0), groupBased(1), officeBased(2);

    private int code;

    private MiscCallInfoDpAssignment(int code) {
        this.code = code;
    }

    public static MiscCallInfoDpAssignment getInstance(int code) {
        switch (code) {
            case 0:
                return MiscCallInfoDpAssignment.individualLine;
            case 1:
                return MiscCallInfoDpAssignment.groupBased;
            case 2:
                return MiscCallInfoDpAssignment.officeBased;
        }

        return null;
    }

    public int getCode() {
        return code;
    }
}
