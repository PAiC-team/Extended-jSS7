
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

/**
 *
 CallTypeCriteria ::= ENUMERATED { forwarded (0), notForwarded (1)}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CallTypeCriteria {
    forwarded(0), notForwarded(1);

    private int code;

    private CallTypeCriteria(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CallTypeCriteria getInstance(int code) {
        switch (code) {
            case 0:
                return CallTypeCriteria.forwarded;
            case 1:
                return CallTypeCriteria.notForwarded;
            default:
                return null;
        }
    }
}
