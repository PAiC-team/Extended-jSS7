
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.CellGlobalIdOrServiceAreaIdFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;

/**
 *
<code>
MetDPCriteriaList {PARAMETERS-BOUND : bound} ::= SEQUENCE SIZE(1..10) OF MetDPCriterion {bound}

MetDPCriterion {PARAMETERS-BOUND : bound} ::= CHOICE {
  enteringCellGlobalId    [0] CellGlobalIdOrServiceAreaIdFixedLength,
  leavingCellGlobalId     [1] CellGlobalIdOrServiceAreaIdFixedLength,
  enteringServiceAreaId   [2] CellGlobalIdOrServiceAreaIdFixedLength,
  leavingServiceAreaId    [3] CellGlobalIdOrServiceAreaIdFixedLength,
  enteringLocationAreaId  [4] LAIFixedLength,
  leavingLocationAreaId   [5] LAIFixedLength,
  inter-SystemHandOverToUMTS [6] NULL,
  inter-SystemHandOverToGSM  [7] NULL,
  inter-PLMNHandOver         [8] NULL,
  inter-MSCHandOver          [9] NULL,
  metDPCriterionAlt          [10] MetDPCriterionAlt {bound}
}
-- The enteringCellGlobalId and leavingCellGlobalId shall contain a Cell Global Identification.
-- The enteringServiceAreaId and leavingServiceAreaId shall contain a Service Area Identification.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MetDPCriterion extends Serializable {

    CellGlobalIdOrServiceAreaIdFixedLength getEnteringCellGlobalId();

    CellGlobalIdOrServiceAreaIdFixedLength getLeavingCellGlobalId();

    CellGlobalIdOrServiceAreaIdFixedLength getEnteringServiceAreaId();

    CellGlobalIdOrServiceAreaIdFixedLength getLeavingServiceAreaId();

    LAIFixedLength getEnteringLocationAreaId();

    LAIFixedLength getLeavingLocationAreaId();

    boolean getInterSystemHandOverToUMTS();

    boolean getInterSystemHandOverToGSM();

    boolean getInterPLMNHandOver();

    boolean getInterMSCHandOver();

    MetDPCriterionAlt getMetDPCriterionAlt();

}
