
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
<code>
DpSpecificCriteriaAlt {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  ...,
  changeOfPositionControlInfo  [0] ChangeOfPositionControlInfo {bound}
  numberOfDigits               [1] NumberOfDigits OPTIONAL,
}

ChangeOfPositionControlInfo {PARAMETERS-BOUND : bound} ::= SEQUENCE SIZE (1..bound.&numOfChangeOfPositionControlInfo) OF ChangeOfLocation {bound}
numOfChangeOfPositionControlInfo ::= 10

NumberOfDigits ::= INTEGER (1..255)
-- Indicates the number of digits to be collected.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DpSpecificCriteriaAlt extends Serializable {

    ArrayList<ChangeOfLocation> getChangeOfPositionControlInfo();

    Integer getNumberOfDigits();

}
