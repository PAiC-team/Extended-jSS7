package org.restcomm.protocols.ss7.inap.api.charging;

import java.io.Serializable;

/**
*
<code>
PulseUnits ::= OCTET STRING (SIZE(1))
-- the PulseUnits is binary coded and has the value range from 0 to 255
</code>
*
* @author sergey vetyutnev
*
*/
public interface PulseUnits extends Serializable {

    int getData();

}
