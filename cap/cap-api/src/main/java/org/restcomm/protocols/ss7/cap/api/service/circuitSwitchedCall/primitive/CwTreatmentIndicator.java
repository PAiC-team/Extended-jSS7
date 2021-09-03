
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

/**
 *
<code>
cwTreatmentIndicator [51] OCTET STRING (SIZE(1)) OPTIONAL,
-- applicable to InitialDP, Connect and ContinueWithArgument
-- acceptCw 'xxxx xx01'B
-- rejectCw 'xxxx xx10'B
-- if absent from Connect or ContinueWithArgument,
-- then CAMEL service does not affect call waiting treatment
</code>
 *
 * @author sergey vetyutnev
 *
 */
public enum CwTreatmentIndicator {

    acceptCw(1), rejectCw(2);

    private int code;

    private CwTreatmentIndicator(int code) {
        this.code = code;
    }

    public static CwTreatmentIndicator getInstance(int code) {
        switch (code & 0x03) {
            case 1:
                return CwTreatmentIndicator.acceptCw;
            case 2:
                return CwTreatmentIndicator.rejectCw;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }
}
