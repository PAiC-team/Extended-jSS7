package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

/**
*
<code>
conferenceTreatmentIndicator [1] OCTET STRING (SIZE(1)) OPTIONAL,
-- acceptConferenceRequest 'xxxx xx01'B
-- rejectConferenceRequest 'xxxx xx10'B
-- network default is accept conference request
</code>

*
* @author sergey vetyutnev
*
*/
public enum ConferenceTreatmentIndicator {

    acceptConferenceRequest(1), rejectConferenceRequest(2);

    private int code;

    private ConferenceTreatmentIndicator(int code) {
        this.code = code;
    }

    public static ConferenceTreatmentIndicator getInstance(int code) {
        switch (code & 0x03) {
            case 1:
                return ConferenceTreatmentIndicator.acceptConferenceRequest;
            case 2:
                return ConferenceTreatmentIndicator.rejectConferenceRequest;
            default:
                return null;
        }
    }

    public int getCode() {
        return this.code;
    }

}
