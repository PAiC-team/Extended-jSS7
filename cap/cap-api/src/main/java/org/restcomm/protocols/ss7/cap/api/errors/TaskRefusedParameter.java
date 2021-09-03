
package org.restcomm.protocols.ss7.cap.api.errors;

/**
 *
 PARAMETER ENUMERATED { generic (0), unobtainable (1), congestion (2) }
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum TaskRefusedParameter {

    generic(0), unobtainable(1), congestion(2);

    private int code;

    private TaskRefusedParameter(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static TaskRefusedParameter getInstance(int code) {
        switch (code) {
            case 0:
                return TaskRefusedParameter.generic;
            case 1:
                return TaskRefusedParameter.unobtainable;
            case 2:
                return TaskRefusedParameter.congestion;
            default:
                return null;
        }
    }
}
