package org.restcomm.protocols.ss7.cap.functional;

/**
 * Steps of CAP functional test
 *
 * @author sergey vetyutnev
 *
 */
public enum FunctionalTestScenario {
    /**
     * TC-BEGIN + InitialDp
     */
    Action_InitilDp(0);

    private int code;

    private FunctionalTestScenario(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
