
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.isup.CallingPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.Digits;
import org.restcomm.protocols.ss7.cap.api.isup.LocationNumberCap;
import org.restcomm.protocols.ss7.cap.api.isup.OriginalCalledNumberCap;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.ScfID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.Carrier;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.NAOliInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ServiceInteractionIndicatorsTwo;

/**
 *
<code>
establishTemporaryConnection {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT EstablishTemporaryConnectionArg {bound}
  RETURN RESULT FALSE
  ERRORS {eTCFailed | missingParameter | systemFailure | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter | unknownCSID}
  CODE opcode-establishTemporaryConnection
}
-- Direction: gsmSCF -> gsmSSF, Timer: T etc
-- This operation is used to create a connection to a resource for a limited period
-- of time (e.g. to play an announcement, to collect user information); it implies
-- the use of the assist procedure. Refer to clause 11 for a description of the
-- procedures associated with this operation.

EstablishTemporaryConnectionArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  assistingSSPIPRoutingAddress    [0] AssistingSSPIPRoutingAddress {bound},
  correlationID                   [1] CorrelationID {bound} OPTIONAL,
  scfID                           [3] ScfID {bound} OPTIONAL,
  extensions                      [4] Extensions {bound} OPTIONAL,
  carrier                         [5] Carrier {bound} OPTIONAL,
  serviceInteractionIndicatorsTwo [6] ServiceInteractionIndicatorsTwo OPTIONAL,
  callSegmentID                   [7] CallSegmentID {bound} OPTIONAL,
  naOliInfo                       [50] NAOliInfo OPTIONAL,
  chargeNumber                    [51] ChargeNumber {bound} OPTIONAL,
  ...,
  originalCalledPartyID           [52] OriginalCalledPartyID {bound} OPTIONAL,
  callingPartyNumber              [53] CallingPartyNumber {bound} OPTIONAL
}

CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..127)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EstablishTemporaryConnectionRequest extends CircuitSwitchedCallMessage {

    /**
     * Use Digits.getGenericNumber() for AssistingSSPIPRoutingAddress
     *
     * @return
     */
    Digits getAssistingSSPIPRoutingAddress();

    /**
     * Use Digits.getGenericDigits() for CorrelationID
     *
     * @return
     */
    Digits getCorrelationID();

    ScfID getScfID();

    CAPExtensions getExtensions();

    Carrier getCarrier();

    ServiceInteractionIndicatorsTwo getServiceInteractionIndicatorsTwo();

    Integer getCallSegmentID();

    NAOliInfo getNAOliInfo();

    LocationNumberCap getChargeNumber();

    OriginalCalledNumberCap getOriginalCalledPartyID();

    CallingPartyNumberCap getCallingPartyNumber();

}