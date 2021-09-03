package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
SubTariffControl ::= BIT STRING {
  oneTimeCharge (0)
} (SIZE(minSubTariffControlLen..maxSubTariffControlLen))
-- The coding of the oneTimeCharge is as follows:
-- 0 - Periodic charge
-- 1 - One time charge

minSubTariffControlLen INTEGER ::= 1
maxSubTariffControlLen INTEGER ::= 8
</code>
*
* @author sergey vetyutnev
*
*/
public interface SubTariffControl extends Serializable {

    boolean getOneTimeCharge();

}
