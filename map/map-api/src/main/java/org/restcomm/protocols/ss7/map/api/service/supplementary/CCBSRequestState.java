
package org.restcomm.protocols.ss7.map.api.service.supplementary;

/**
 *
 CCBS-RequestState ::= ENUMERATED { request (0), recall (1), active (2), completed (3), suspended (4), frozen (5), deleted (6)
 * }
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CCBSRequestState {

    request(0), recall(1), active(2), completed(3), suspended(4), frozen(5), deleted(6);

    private int code;

    private CCBSRequestState(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CCBSRequestState getInstance(int code) {
        switch (code) {
            case 0:
                return CCBSRequestState.request;
            case 1:
                return CCBSRequestState.recall;
            case 2:
                return CCBSRequestState.active;
            case 3:
                return CCBSRequestState.completed;
            case 4:
                return CCBSRequestState.suspended;
            case 5:
                return CCBSRequestState.frozen;
            case 6:
                return CCBSRequestState.deleted;
            default:
                return null;
        }
    }
}
