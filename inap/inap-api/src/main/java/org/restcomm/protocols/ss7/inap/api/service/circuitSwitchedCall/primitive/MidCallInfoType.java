package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.Digits;

/**
*
<code>
MidCallInfoType {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  iNServiceControlCodeLow  [0] Digits {bound},
  iNServiceControlCodeHigh [1] Digits {bound} OPTIONAL,
  ...
}
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface MidCallInfoType extends Serializable {

    Digits getINServiceControlCodeLow();

    Digits getINServiceControlCodeHigh();

}
