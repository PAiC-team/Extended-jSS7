package org.restcomm.protocols.ss7.inap.api.primitives;

/**
*
<code>
TerminalType ::= ENUMERATED {
unknown (0),
dialPulse (1),
dtmf (2),
isdn (3),
isdnNoDtmf (4),
spare (16)
}
-- Identifies the terminal type so that the SCF can specify, to the SRF,
-- the appropriate type of capability
-- (voice recognition, DTMF, display capability, etc.).
</code>
*
* @author sergey vetyutnev
*
*/
public enum TerminalType {

    unknown(0), dialPulse(1), dtmf(2), isdn(3), isdnNoDtmf(4), spare(16);

    private int code;

    private TerminalType(int code) {
        this.code = code;
    }

    public static TerminalType getInstance(int code) {
        switch (code) {
        case 0:
            return TerminalType.unknown;
        case 1:
            return TerminalType.dialPulse;
        case 2:
            return TerminalType.dtmf;
        case 3:
            return TerminalType.isdn;
        case 4:
            return TerminalType.isdnNoDtmf;
        case 16:
            return TerminalType.spare;
        }

        return null;
    }

    public int getCode() {
        return code;
    }

}
