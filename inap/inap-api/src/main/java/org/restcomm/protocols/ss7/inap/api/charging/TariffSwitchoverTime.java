package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
TariffSwitchoverTime ::= OCTET STRING (SIZE(1))
-- This time is the absolute time at which the next tariff has to become active. It is represented in steps of 15 minutes.
-- The coding is the following:
-- 0 : spare
-- 1 : 0 hour 15 minutes
-- 2 : 0 hour 30 minutes
-- 3 : 0 hour 45 minutes
-- 4 : 1 hour 0 minutes
-- ..
-- 96 : 24 hours 0 minutes
-- 97-255 : spare
</code>
*
* @author sergey vetyutnev
*
*/
public interface TariffSwitchoverTime extends Serializable {

    int getData();

}
