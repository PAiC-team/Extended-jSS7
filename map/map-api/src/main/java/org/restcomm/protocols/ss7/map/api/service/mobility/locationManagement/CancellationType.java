
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

/**
 *
 CancellationType ::= ENUMERATED {
   updateProcedure (0), subscriptionWithdraw (1), ..., initialAttachProcedure (2)}
 -- The HLR shall not send values other than listed above
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum CancellationType {

    updateProcedure(0), subscriptionWithdraw(1), initialAttachProcedure(2);

    private int code;

    private CancellationType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static CancellationType getInstance(int code) {
        switch (code) {
            case 0:
                return CancellationType.updateProcedure;
            case 1:
                return CancellationType.subscriptionWithdraw;
            case 2:
                return CancellationType.initialAttachProcedure;
            default:
                return null;
        }
    }
}
