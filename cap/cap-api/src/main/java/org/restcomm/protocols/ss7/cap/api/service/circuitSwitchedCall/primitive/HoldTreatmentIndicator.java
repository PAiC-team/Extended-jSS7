
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

/**
 *
<code>
holdTreatmentIndicator [50] OCTET STRING (SIZE(1)) OPTIONAL,
-- applicable to InitialDP, Connect and ContinueWithArgument
-- acceptHoldRequest 'xxxx xx01'B
-- rejectHoldRequest 'xxxx xx10'B
-- if absent from Connect or ContinueWithArgument,
-- then CAMEL service does not affect call hold treatment
</code>
 *
 * @author sergey vetyutnev
 *
 */
public enum HoldTreatmentIndicator {

    acceptHoldRequest(1), rejectHoldRequest(2);

    private int code;

    private HoldTreatmentIndicator(int code) {
        this.code = code;
    }

    public static HoldTreatmentIndicator getInstance(int code) {
        switch (code & 0x03) {
            case 1:
                return HoldTreatmentIndicator.acceptHoldRequest;
            case 2:
                return HoldTreatmentIndicator.rejectHoldRequest;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
