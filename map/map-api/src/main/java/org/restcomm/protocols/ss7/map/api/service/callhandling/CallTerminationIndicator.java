
package org.restcomm.protocols.ss7.map.api.service.callhandling;

/**
 *
 CallTerminationIndicator ::= ENUMERATED { terminateCallActivityReferred (0), terminateAllCallActivities (1), ...} --
 * exception handling: -- reception of values 2-10 shall be mapped to ' terminateCallActivityReferred ' -- reception of values >
 * 10 shall be mapped to ' terminateAllCallActivities '
 *
 * -- In MSCs not supporting linkage of all call activities, any value received shall -- be interpreted as '
 * terminateCallActivityReferred '
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CallTerminationIndicator {

    terminateCallActivityReferred(0), terminateAllCallActivities(1);

    private int code;

    private CallTerminationIndicator(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CallTerminationIndicator getInstance(int code) {
        if (code == 0 || code >= 2 && code <= 10)
            return CallTerminationIndicator.terminateCallActivityReferred;
        else
            return CallTerminationIndicator.terminateAllCallActivities;
    }
}
