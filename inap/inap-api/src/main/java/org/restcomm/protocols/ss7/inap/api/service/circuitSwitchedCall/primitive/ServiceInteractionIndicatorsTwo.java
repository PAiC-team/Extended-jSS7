package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.primitives.BothwayThroughConnectionInd;

/**
*
<code>
ServiceInteractionIndicatorsTwo ::= SEQUENCE {
forwardServiceInteractionInd [0] ForwardServiceInteractionInd OPTIONAL,
-- applicable to operations IDP, CON, ICA, CWA.
backwardServiceInteractionInd [1] BackwardServiceInteractionInd OPTIONAL,
-- applicable to operations IDP, CON, CTR, CWA, ETC.
bothwayThroughConnectionInd [2] BothwayThroughConnectionInd OPTIONAL,
-- applicable to operations CTR, ETC.
suspendTimer [3] SuspendTimer OPTIONAL,
-- applicable to operations CON, ICA, CWA.
connectedNumberTreatmentInd [4] ConnectedNumberTreatmentInd OPTIONAL,
-- applicable to operations CON, CTR, CWA, ETC.
suppressCallDiversionNotification [5] BOOLEAN OPTIONAL,
-- applicable to CON, ICA, CWA
suppressCallTransferNotification [6] BOOLEAN OPTIONAL,
-- applicable to CON, ICA, CWA
allowCdINNoPresentationInd [7] BOOLEAN OPTIONAL,
-- applicable to CON, ICA, CWA
-- indicates whether the Number Presentation not allowed indicator of the ISUP
-- "called IN number" shall be set to presentation allowed (TRUE) or
-- presentation not allowed (FALSE)
userDialogueDurationInd [8] BOOLEAN DEFAULT TRUE,
-- applicable when interaction with the user is required, if the interaction
-- TRUE means the user interaction may last longer than 90 seconds. Otherwise the
-- indicator should be set to FALSE.
-- used for delaying ISUP T9 timer.
...
}
-- Indicators which are exchanged between SSP and SCP to resolve interactions between IN based services
-- and network based services, respectively between different IN based services.

SuspendTimer ::= INTEGER (-1..120) --value in seconds
-- The default is as specified in EN 301 070-1
-- The value -1 indicates the network specific suspend timer (T6) is to be used.
</code>

*
* @author sergey vetyutnev
*
*/
public interface ServiceInteractionIndicatorsTwo extends Serializable {

    ForwardServiceInteractionInd getForwardServiceInteractionInd();

    BackwardServiceInteractionInd getBackwardServiceInteractionInd();

    BothwayThroughConnectionInd getBothwayThroughConnectionInd();

    Integer getSuspendTimer();

    ConnectedNumberTreatmentInd getConnectedNumberTreatmentInd();

    Boolean getSuppressCallDiversionNotification();

    Boolean getSuppressCallTransferNotification();

    Boolean getAllowCdINNoPresentationInd();

    Boolean getUserDialogueDurationInd();

}
