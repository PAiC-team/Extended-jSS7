
package org.restcomm.protocols.ss7.map.api.service.callhandling;

/**
 *
 *
 * -- callDiversionAllowed (xxxx xx01) -- callDiversionNotAllowed (xxxx xx10) -- network default is call diversion allowed
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CallDiversionTreatmentIndicatorValue {

    callDiversionAllowed(1), callDiversionNotAllowed(2);

    private int code;

    private CallDiversionTreatmentIndicatorValue(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CallDiversionTreatmentIndicatorValue getInstance(int code) {
        switch (code) {
            case 1:
                return CallDiversionTreatmentIndicatorValue.callDiversionAllowed;
            case 2:
                return CallDiversionTreatmentIndicatorValue.callDiversionNotAllowed;
            default:
                return null;
        }
    }

}
