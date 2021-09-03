package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
BackwardServiceInteractionInd ::= SEQUENCE {
conferenceTreatmentIndicator [1] OCTET STRING (SIZE(1)) OPTIONAL,
-- acceptConferenceRequest 'xxxx xx01'B
-- rejectConferenceRequest 'xxxx xx10'B
-- network default is accept conference request,
callCompletionTreatmentIndicator [2] OCTET STRING (SIZE(1)) OPTIONAL
-- acceptCallCompletionServiceRequest 'xxxx xx01'B,
-- rejectCallCompletionServiceRequest 'xxxx xx10'B
-- network default is accept call completion service request
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
