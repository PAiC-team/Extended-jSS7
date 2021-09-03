
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

/**
 *
<code>
ectTreatmentIndicator [52] OCTET STRING (SIZE(1)) OPTIONAL,
-- applicable to InitialDP, Connect and ContinueWithArgument
-- acceptEctRequest 'xxxx xx01'B
-- rejectEctRequest 'xxxx xx10'B
-- if absent from Connect or ContinueWithArgument,
-- then CAMEL service does not affect explicit call transfer treatment
</code>
 *
 * @author sergey vetyutnev
 *
 */
public enum EctTreatmentIndicator {

    acceptEctRequest(1), rejectEctRequest(2);

    private int code;

    private EctTreatmentIndicator(int code) {
        this.code = code;
    }

    public static EctTreatmentIndicator getInstance(int code) {
        switch (code & 0x03) {
            case 1:
                return EctTreatmentIndicator.acceptEctRequest;
            case 2:
                return EctTreatmentIndicator.rejectEctRequest;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
