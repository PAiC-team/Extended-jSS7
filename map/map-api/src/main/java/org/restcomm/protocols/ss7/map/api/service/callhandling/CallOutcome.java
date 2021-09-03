
package org.restcomm.protocols.ss7.map.api.service.callhandling;

/**
 *
 CallOutcome ::= ENUMERATED { success (0), failure (1), busy (2), ...} -- exception handling: -- reception of values 3-10
 * shall be mapped to 'success' -- reception of values 11-20 shall be mapped to 'failure' -- reception of values > 20 shall be
 * mapped to 'busy'
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CallOutcome {

    success(0), failure(1), busy(2);

    private int code;

    private CallOutcome(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CallOutcome getInstance(int code) {

        if (code == 0 || code >= 3 && code <= 10)
            return CallOutcome.success;
        else if (code == 1 || code >= 11 && code <= 20)
            return CallOutcome.failure;
        else
            return CallOutcome.busy;
    }
}
