
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

/**
 *
<code>
-- As specified in ITU-T Recommendation Q.763 as follows:
-- no indication 'xxxx xx00'B
-- no charge 'xxxx xx01'B
-- charge 'xxxx xx10'B
-- spare 'xxxx xx11'B
-- Sending entity shall fill the upper six bits with '0's.
-- Receiving entity shall ignore the upper six bits.
<code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public enum ChargeIndicatorValue {

    noIndication(0), noCharge(1), charge(2), spare(3);

    private int code;

    private ChargeIndicatorValue(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ChargeIndicatorValue getInstance(int code) {
        switch (code) {
            case 0:
                return ChargeIndicatorValue.noIndication;
            case 1:
                return ChargeIndicatorValue.noCharge;
            case 2:
                return ChargeIndicatorValue.charge;
            case 3:
                return ChargeIndicatorValue.spare;
            default:
                return null;
        }
    }
}
