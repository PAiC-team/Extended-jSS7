
package org.restcomm.protocols.ss7.cap.api.errors;

/**
 *
 PARAMETER SEQUENCE { problem [0] ENUMERATED { unknownOperation (0), tooLate (1), operationNotCancellable (2) },
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CancelProblem {

    unknownOperation(0), tooLate(1), operationNotCancellable(2);

    private int code;

    private CancelProblem(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CancelProblem getInstance(int code) {
        switch (code) {
            case 0:
                return CancelProblem.unknownOperation;
            case 1:
                return CancelProblem.tooLate;
            case 2:
                return CancelProblem.operationNotCancellable;
            default:
                return null;
        }
    }
}
