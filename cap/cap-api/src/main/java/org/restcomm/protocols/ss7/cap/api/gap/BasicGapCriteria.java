
package org.restcomm.protocols.ss7.cap.api.gap;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.Digits;

/**
 *
<code>
BasicGapCriteria {PARAMETERS-BOUND : bound} ::= CHOICE {
  calledAddressValue         [0] Digits {bound},
  gapOnService               [2] GapOnService,
  calledAddressAndService    [29] SEQUENCE {
    calledAddressValue [0] Digits {bound},
    serviceKey         [1] ServiceKey,
    ...
  },
  callingAddressAndService   [30] SEQUENCE {
    callingAddressValue [0] Digits {bound},
    serviceKey          [1] ServiceKey,
    ...
  }
}
-- Both calledAddressValue and callingAddressValue can be
-- incomplete numbers, in the sense that a limited amount of digits can be given.
-- For the handling of numbers starting with the same digit string refer to the detailed
-- procedure of the CallGap operation
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface BasicGapCriteria extends Serializable {

    Digits getCalledAddressValue();

    GapOnService getGapOnService();

    CalledAddressAndService getCalledAddressAndService();

    CallingAddressAndService getCallingAddressAndService();

}
