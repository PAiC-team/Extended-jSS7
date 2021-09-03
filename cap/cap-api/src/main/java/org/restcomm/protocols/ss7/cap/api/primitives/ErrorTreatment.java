
package org.restcomm.protocols.ss7.cap.api.primitives;

/**
 *
 ErrorTreatment ::= ENUMERATED { stdErrorAndInfo (0), help (1), repeatPrompt (2) } -- stdErrorAndInfomeans returning the
 * 'ImproperCallerResponse' error in the event of an error -- condition during collection of user info.
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum ErrorTreatment {

    stdErrorAndInfo(0), help(1), repeatPrompt(2);

    private int code;

    private ErrorTreatment(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ErrorTreatment getInstance(int code) {
        switch (code) {
            case 0:
                return ErrorTreatment.stdErrorAndInfo;
            case 1:
                return ErrorTreatment.help;
            case 2:
                return ErrorTreatment.repeatPrompt;
            default:
                return null;
        }
    }
}
