package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
ForwardServiceInteractionInd ::= SEQUENCE {
conferenceTreatmentIndicator [1] OCTET STRING (SIZE(1)) OPTIONAL,
-- acceptConferenceRequest 'xxxx xx01'B
-- rejectConferenceRequest 'xxxx xx10'B
-- network default is accept conference request
callDiversionTreatmentIndicator [2] OCTET STRING (SIZE(1)) OPTIONAL,
-- callDiversionAllowed 'xxxx xx01'B
-- callDiversionNotAllowed 'xxxx xx10'B
-- network default is Call Diversion allowed
callOfferingTreatmentIndicator [3] OCTET STRING (SIZE(1)) OPTIONAL,
-- callOfferingNotAllowed 'xxxx xx01'B,
-- callOfferingAllowed 'xxxx xx10'B
-- network default is Call Offering not allowed
callingPartyRestrictionIndicator [4] OCTET STRING (SIZE(1)) OPTIONAL,
-- noINImpact 'xxxx xx01'B,
-- presentationRestricted 'xxxx xx10'B
-- network default is noINImpact
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

    CallOfferingTreatmentIndicator getCallOfferingTreatmentIndicator();

    CallingPartyRestrictionIndicator getCallingPartyRestrictionIndicator();

}
