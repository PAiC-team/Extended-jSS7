
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;

/**
 *
<code>
ChangeOfPositionControlInfo {PARAMETERS-BOUND : bound} ::= SEQUENCE SIZE (1..bound.&numOfChangeOfPositionControlInfo) OF ChangeOfLocation {bound}

ChangeOfLocation {PARAMETERS-BOUND : bound} ::= CHOICE {
  cellGlobalId     [0] CellGlobalIdOrServiceAreaIdFixedLength,
  serviceAreaId    [1] CellGlobalIdOrServiceAreaIdFixedLength,
  locationAreaId   [2] LAIFixedLength,
  inter-SystemHandOver  [3] NULL,
  inter-PLMNHandOver    [4] NULL,
  inter-MSCHandOver     [5] NULL,
  changeOfLocationAlt   [6] ChangeOfLocationAlt {bound}
}
-- The cellGlobalId shall contain a Cell Global Identification.
-- The serviceAreaId shall contain a Service Area Identification.

ChangeOfLocationAlt {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ChangeOfLocation extends Serializable {

    CellGlobalIdOrServiceAreaIdFixedLength getCellGlobalId();

    CellGlobalIdOrServiceAreaIdFixedLength getServiceAreaId();

    LAIFixedLength getLocationAreaId();

    boolean isInterSystemHandOver();

    boolean isInterPLMNHandOver();

    boolean isInterMSCHandOver();

    ChangeOfLocationAlt getChangeOfLocationAlt();

}
