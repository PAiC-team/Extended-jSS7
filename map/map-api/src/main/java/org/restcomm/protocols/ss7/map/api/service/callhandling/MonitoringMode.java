
package org.restcomm.protocols.ss7.map.api.service.callhandling;

/**
 *
 MonitoringMode ::= ENUMERATED { a-side (0), b-side (1), ...} -- exception handling: -- reception of values 2-10 shall be
 * mapped 'a-side' -- reception of values > 10 shall be mapped to 'b-side'
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum MonitoringMode {

    aSide(0), bSide(1);

    private int code;

    private MonitoringMode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static MonitoringMode getInstance(int code) {

        if (code == 0 || code >= 2 && code <= 10)
            return MonitoringMode.aSide;
        else
            return MonitoringMode.bSide;
    }
}
