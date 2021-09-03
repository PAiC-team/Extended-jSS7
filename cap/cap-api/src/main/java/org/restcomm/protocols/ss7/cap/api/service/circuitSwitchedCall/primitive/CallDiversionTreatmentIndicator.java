
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

/**
 *
<code>
callDiversionTreatmentIndicator [2] OCTET STRING (SIZE(1)) OPTIONAL,
-- callDiversionAllowed 'xxxx xx01'B
-- callDiversionNotAllowed 'xxxx xx10'B
-- if absent from Connect or ContinueWithArgument,
-- then CAMEL service does not affect call diversion treatment
</code>
 *
 * @author sergey vetyutnev
 *
 */
public enum CallDiversionTreatmentIndicator {
    callDiversionAllowed(1), callDiversionNotAllowed(2);

    private int code;

    private CallDiversionTreatmentIndicator(int code) {
        this.code = code;
    }

    public static CallDiversionTreatmentIndicator getInstance(int code) {
        switch (code & 0x03) {
            case 1:
                return CallDiversionTreatmentIndicator.callDiversionAllowed;
            case 2:
                return CallDiversionTreatmentIndicator.callDiversionNotAllowed;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
