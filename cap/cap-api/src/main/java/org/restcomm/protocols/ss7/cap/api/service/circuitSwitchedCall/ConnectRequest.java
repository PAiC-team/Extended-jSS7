
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.cap.api.isup.GenericNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.LocationNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.OriginalCalledNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.RedirectingPartyIDCap;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AlertingPatternCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Carrier;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.DestinationRoutingAddress;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.NAOliInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ServiceInteractionIndicatorsTwo;
import org.restcomm.protocols.ss7.inap.api.isup.CallingPartysCategoryInap;
import org.restcomm.protocols.ss7.inap.api.isup.RedirectionInformationInap;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGInterlock;

/**
 *
<code>
connect {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT ConnectArg {bound}
  RETURN RESULT FALSE
  ERRORS {missingParameter | parameterOutOfRange | systemFailure | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter | unknownLegID}
  CODE opcode-connect
}
-- Direction: gsmSCF-> gsmSSF, Timer: Tcon
-- This operation is used to request the gsmSSF to perform the call processing actions
-- to route or forward a call to a specified destination.

ConnectArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  destinationRoutingAddress     [0] DestinationRoutingAddress {bound},
  alertingPattern               [1] AlertingPattern OPTIONAL,
  originalCalledPartyID         [6] OriginalCalledPartyID {bound} OPTIONAL,
  extensions                    [10] Extensions {bound} OPTIONAL,
  carrier                       [11] Carrier {bound} OPTIONAL,
  callingPartysCategory         [28] CallingPartysCategory OPTIONAL,
  redirectingPartyID            [29] RedirectingPartyID {bound} OPTIONAL,
  redirectionInformation        [30] RedirectionInformation OPTIONAL,
  genericNumbers                [14] GenericNumbers {bound} OPTIONAL,
  serviceInteractionIndicatorsTwo [15] ServiceInteractionIndicatorsTwo OPTIONAL,
  chargeNumber                  [19] ChargeNumber {bound} OPTIONAL,
  legToBeConnected              [21] LegID OPTIONAL,
  cug-Interlock                 [31] CUG-Interlock OPTIONAL,
  cug-OutgoingAccess            [32] NULL OPTIONAL,
  suppressionOfAnnouncement     [55] SuppressionOfAnnouncement OPTIONAL,
  oCSIApplicable                [56] OCSIApplicable OPTIONAL,
  naOliInfo                     [57] NAOliInfo OPTIONAL,
  bor-InterrogationRequested    [58] NULL OPTIONAL,
  ...
  suppress-N-CSI                [59] NULL OPTIONAL
}
-- na-Info is included at the discretion of the gsmSCF operator.

SuppressionOfAnnouncement ::= NULL

OCSIApplicable ::= NULL
-- Indicates that the Originating CAMEL Subscription Information, if present, shall be
-- applied on the outgoing call leg created with a Connect operation. For the use of this
-- parameter see 3GPP TS 23.078 [7].
</code>
 *
 *
 * @author sergey vetyutnev
 * @author tamas gyorgyey
 */
public interface ConnectRequest extends CircuitSwitchedCallMessage {

    DestinationRoutingAddress getDestinationRoutingAddress();

    AlertingPatternCap getAlertingPattern();

    OriginalCalledNumberCap getOriginalCalledPartyID();

    CAPExtensions getExtensions();

    Carrier getCarrier();

    CallingPartysCategoryInap getCallingPartysCategory();

    RedirectingPartyIDCap getRedirectingPartyID();

    RedirectionInformationInap getRedirectionInformation();

    ArrayList<GenericNumberCap> getGenericNumbers();

    ServiceInteractionIndicatorsTwo getServiceInteractionIndicatorsTwo();

    LocationNumberCap getChargeNumber();

    LegID getLegToBeConnected();

    CUGInterlock getCUGInterlock();

    boolean getCugOutgoingAccess();

    boolean getSuppressionOfAnnouncement();

    boolean getOCSIApplicable();

    NAOliInfo getNAOliInfo();

    boolean getBorInterrogationRequested();

    boolean getSuppressNCSI();

}
