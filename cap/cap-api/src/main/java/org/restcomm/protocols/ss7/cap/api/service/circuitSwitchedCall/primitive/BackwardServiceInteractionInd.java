
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
BackwardServiceInteractionInd ::= SEQUENCE {
  conferenceTreatmentIndicator      [1] OCTET STRING (SIZE(1)) OPTIONAL,
  -- acceptConferenceRequest 'xxxx xx01'B
  -- rejectConferenceRequest 'xxxx xx10'B
  -- if absent from Connect or ContinueWithArgument,
  -- then CAMEL service does not affect conference treatement

  callCompletionTreatmentIndicator  [2] OCTET STRING (SIZE(1)) OPTIONAL,
  -- acceptCallCompletionServiceRequest 'xxxx xx01'B,
  -- rejectCallCompletionServiceRequest 'xxxx xx10'B
  -- if absent from Connect or ContinueWithArgument,
  -- then CAMEL service does not affect call completion treatment
  ...
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface BackwardServiceInteractionInd extends Serializable {

    ConferenceTreatmentIndicator getConferenceTreatmentIndicator();

    CallCompletionTreatmentIndicator getCallCompletionTreatmentIndicator();

}
