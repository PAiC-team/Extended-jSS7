
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import java.util.List;

import org.restcomm.protocols.ss7.cap.api.isup.GenericNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.LocationNumberCap;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AlertingPatternCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Carrier;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ContinueWithArgumentArgExtension;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.NAOliInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ServiceInteractionIndicatorsTwo;
import org.restcomm.protocols.ss7.inap.api.isup.CallingPartysCategoryInap;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGInterlock;

/**
 *
 continueWithArgument {PARAMETERS-BOUND : bound} OPERATION ::= {
   ARGUMENT ContinueWithArgumentArg {bound}
   RETURN RESULT FALSE
   ERRORS {missingParameter | parameterOutOfRange | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter |
           unknownLegID | unknownCSID} CODE opcode-continueWithArgument}
-- Direction: gsmSCF -> gsmSSF, Timer: T cwa
-- This operation is used to request the gsmSSF to proceed with call processing at the
-- DP at which it previously suspended call processing to await gsmSCF instructions
-- (i.e. proceed to the next point in call in the BCSM). The gsmSSF continues call
-- processing with the modified call setup information as received from the gsmSCF.

ContinueWithArgumentArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  alertingPattern                    [1] AlertingPattern OPTIONAL,
  extensions                         [6] Extensions {bound} OPTIONAL,
  serviceInteractionIndicatorsTwo    [7] ServiceInteractionIndicatorsTwo OPTIONAL,
  callingPartysCategory              [12] CallingPartysCategory OPTIONAL,
  genericNumbers                     [16] GenericNumbers {bound} OPTIONAL,
  cug-Interlock                      [17] CUG-Interlock OPTIONAL,
  cug-OutgoingAccess                 [18] NULL OPTIONAL,
  chargeNumber                       [50] ChargeNumber {bound} OPTIONAL,
  carrier                            [52] Carrier {bound} OPTIONAL,
  suppressionOfAnnouncement          [55] SuppressionOfAnnouncement OPTIONAL,
  naOliInfo                          [56] NAOliInfo OPTIONAL,
  bor-InterrogationRequested         [57] NULL OPTIONAL,
  suppress-O-CSI                     [58] NULL OPTIONAL,
  continueWithArgumentArgExtension   [59] ContinueWithArgumentArgExtension {bound} OPTIONAL,
  ...
}

GenericNumbers {PARAMETERS-BOUND : bound} ::= SET SIZE(1..bound.&numOfGenericNumbers) OF GenericNumber {bound}

SuppressionOfAnnouncement ::= NULL

 *
 * @author sergey vetyutnev
 *
 */
public interface ContinueWithArgumentRequest extends CircuitSwitchedCallMessage {

    AlertingPatternCap getAlertingPattern();

    CAPExtensions getExtensions();

    ServiceInteractionIndicatorsTwo getServiceInteractionIndicatorsTwo();

    CallingPartysCategoryInap getCallingPartysCategory();

    List<GenericNumberCap> getGenericNumbers();

    CUGInterlock getCugInterlock();

    boolean getCugOutgoingAccess();

    LocationNumberCap getChargeNumber();

    Carrier getCarrier();

    boolean getSuppressionOfAnnouncement();

    NAOliInfo getNaOliInfo();

    boolean getBorInterrogationRequested();

    boolean getSuppressOCsi();

    ContinueWithArgumentArgExtension getContinueWithArgumentArgExtension();

}
