package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.Digits;

/**
*
<code>
MidCallInfo {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  iNServiceControlCode [0] Digits {bound},
  ...
}
</code>
*
* @author sergey vetyutnev
*
*/
public interface MidCallInfo extends Serializable {

    Digits getINServiceControlCode();

}
