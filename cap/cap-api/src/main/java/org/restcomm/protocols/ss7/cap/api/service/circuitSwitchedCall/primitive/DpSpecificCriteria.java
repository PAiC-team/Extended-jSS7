
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
DpSpecificCriteria {PARAMETERS-BOUND : bound}::= CHOICE {
  applicationTimer       [1] ApplicationTimer,
  midCallControlInfo     [2] MidCallControlInfo,
  dpSpecificCriteriaAlt  [3] DpSpecificCriteriaAlt {bound}
}
-- Exception handling: reception of DpSpecificCriteriaAlt shall be treated like
-- reception of no DpSpecificCriteria.
-- The gsmSCF may set a timer in the gsmSSF for the No_Answer event.
-- If the user does not answer the call within the allotted time,
-- then the gsmSSF reports the event to the gsmSCF.
-- The gsmSCF may define a criterion for the detection of DTMF digits during a call.
-- The gsmSCF may define other criteria in the dpSpecificCriteriaAlt alternative
-- in future releases.

ApplicationTimer ::=INTEGER (0..2047)
-- Used by the gsmSCF to set a timer in the gsmSSF. The timer is in seconds.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DpSpecificCriteria extends Serializable {

    Integer getApplicationTimer();

    MidCallControlInfo getMidCallControlInfo();

    DpSpecificCriteriaAlt getDpSpecificCriteriaAlt();

}
