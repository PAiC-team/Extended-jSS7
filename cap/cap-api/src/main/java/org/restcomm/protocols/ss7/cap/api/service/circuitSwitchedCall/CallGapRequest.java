
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.gap.GapCriteria;
import org.restcomm.protocols.ss7.cap.api.gap.GapIndicators;
import org.restcomm.protocols.ss7.cap.api.gap.GapTreatment;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ControlType;

/**
 *
<code>
callGap {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT CallGapArg {bound}
  RETURN RESULT FALSE
  ALWAYS RESPONDS FALSE
  CODE opcode-callGap
}
-- Direction: gsmSCF -> gsmSSF, Timer: T cg
-- This operation is used to request the gsmSSF to reduce the rate at which specific service
-- requests are sent to the gsmSCF.

CallGapArg {PARAMETERS-BOUND : bound}::= SEQUENCE {
  gapCriteria    [0] GapCriteria {bound},
  gapIndicators  [1] GapIndicators,
  controlType    [2] ControlType OPTIONAL
  gapTreatment   [3] GapTreatment {bound} OPTIONAL
  extensions     [4] Extensions {bound} OPTIONAL,
  ...
}
-- OPTIONAL denotes network operator optional. If gapTreatment is not present, then the gsmSSF will
-- use a default treatment depending on network operator implementation.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallGapRequest extends CircuitSwitchedCallMessage {

    GapCriteria getGapCriteria();

    GapIndicators getGapIndicators();

    ControlType getControlType();

    GapTreatment getGapTreatment();

    CAPExtensions getExtensions();

}