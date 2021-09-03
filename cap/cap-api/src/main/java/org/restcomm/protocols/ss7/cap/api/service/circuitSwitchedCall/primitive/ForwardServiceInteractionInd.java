
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
ForwardServiceInteractionInd ::= SEQUENCE {
  conferenceTreatmentIndicator       [1] OCTET STRING (SIZE(1)) OPTIONAL,
  -- acceptConferenceRequest 'xxxx xx01'B
  -- rejectConferenceRequest 'xxxx xx10'B
  -- if absent from Connect or ContinueWithArgument,
  -- then CAMEL service does not affect conference treatment

  callDiversionTreatmentIndicator    [2] OCTET STRING (SIZE(1)) OPTIONAL,
  -- callDiversionAllowed 'xxxx xx01'B
  -- callDiversionNotAllowed 'xxxx xx10'B
  -- if absent from Connect or ContinueWithArgument,
  -- then CAMEL service does not affect call diversion treatment

  callingPartyRestrictionIndicator   [4] OCTET STRING (SIZE(1)) OPTIONAL,
  -- noINImpact 'xxxx xx01'B
  -- presentationRestricted 'xxxx xx10'B
  -- if absent from Connect or ContinueWithArgument,
  -- then CAMEL service does not affect calling party restriction treatment
  ...
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface ForwardServiceInteractionInd extends Serializable {

    ConferenceTreatmentIndicator getConferenceTreatmentIndicator();

    CallDiversionTreatmentIndicator getCallDiversionTreatmentIndicator();

    CallingPartyRestrictionIndicator getCallingPartyRestrictionIndicator();

}
