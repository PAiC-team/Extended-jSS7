package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

/**
*
<code>
callOfferingTreatmentIndicator [3] OCTET STRING (SIZE(1)) OPTIONAL,
-- callOfferingNotAllowed 'xxxx xx01'B,
-- callOfferingAllowed 'xxxx xx10'B
-- network default is Call Offering not allowed
</code>

*
* @author sergey vetyutnev
*
*/
public enum CallOfferingTreatmentIndicator {
    callOfferingNotAllowed(1), callOfferingAllowed(2);

    private int code;

    private CallOfferingTreatmentIndicator(int code) {
        this.code = code;
    }

    public static CallOfferingTreatmentIndicator getInstance(int code) {
        switch (code & 0x03) {
            case 1:
                return CallOfferingTreatmentIndicator.callOfferingNotAllowed;
            case 2:
                return CallOfferingTreatmentIndicator.callOfferingAllowed;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }

}
