
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

/**
 *
<code>
ChargeIndicator ::= OCTET STRING (SIZE (1))
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
public interface ChargeIndicator extends Serializable {

    int getData();

    ChargeIndicatorValue getChargeIndicatorValue();

}
