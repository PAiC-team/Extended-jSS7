package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

/**
*
<code>
callingPartyRestrictionIndicator [4] OCTET STRING (SIZE(1)) OPTIONAL,
-- noINImpact 'xxxx xx01'B,
-- presentationRestricted 'xxxx xx10'B
-- network default is noINImpact
</code>

*
* @author sergey vetyutnev
*
*/
public enum CallingPartyRestrictionIndicator {
    noINImpact(1), presentationRestricted(2);

    private int code;

    private CallingPartyRestrictionIndicator(int code) {
        this.code = code;
    }

    public static CallingPartyRestrictionIndicator getInstance(int code) {
        switch (code & 0x03) {
            case 1:
                return CallingPartyRestrictionIndicator.noINImpact;
            case 2:
                return CallingPartyRestrictionIndicator.presentationRestricted;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }

}
