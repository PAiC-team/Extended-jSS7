package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

/**
*
<code>
callCompletionTreatmentIndicator [2] OCTET STRING (SIZE(1)) OPTIONAL
-- acceptCallCompletionServiceRequest 'xxxx xx01'B,
-- rejectCallCompletionServiceRequest 'xxxx xx10'B
-- network default is accept call completion service request
</code>
*
* @author sergey vetyutnev
*
*/
public enum CallCompletionTreatmentIndicator {

    acceptCallCompletionServiceRequest(1), rejectCallCompletionServiceRequest(2);

    private int code;

    private CallCompletionTreatmentIndicator(int code) {
        this.code = code;
    }

    public static CallCompletionTreatmentIndicator getInstance(int code) {
        switch (code & 0x03) {
            case 1:
                return CallCompletionTreatmentIndicator.acceptCallCompletionServiceRequest;
            case 2:
                return CallCompletionTreatmentIndicator.rejectCallCompletionServiceRequest;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }

}
