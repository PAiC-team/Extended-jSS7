
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.isup.CallingPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.DestinationRoutingAddress;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallReferenceNumber;

/**
 *
<code>
initiateCallAttempt {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT InitiateCallAttemptArg {bound}
  RESULT InitiateCallAttemptRes {bound}
  ERRORS {
    missingParameter | parameterOutOfRange | systemFailure | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter
  }
  CODE opcode-initiateCallAttempt}
-- Direction: gsmSCF -> gsmSSF, Timer T ica
-- This operation is used to instruct the gsmSSF to create a new call to a call party using the
-- address information provided by the gsmSCF.

InitiateCallAttemptArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  destinationRoutingAddress  [0] DestinationRoutingAddress {bound},
  extensions                 [4] Extensions {bound} OPTIONAL,
  legToBeCreated             [5] LegID OPTIONAL,
  newCallSegment             [6] CallSegmentID {bound} OPTIONAL,
  callingPartyNumber         [30] CallingPartyNumber {bound} OPTIONAL,
  callReferenceNumber        [51] CallReferenceNumber OPTIONAL,
  gsmSCFAddress              [52] ISDN-AddressString OPTIONAL,
  suppress-T-CSI             [53] NULL OPTIONAL,
  ...
}

CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..127)
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface InitiateCallAttemptRequest extends CircuitSwitchedCallMessage {

    DestinationRoutingAddress getDestinationRoutingAddress();

    CAPExtensions getExtensions();

    LegID getLegToBeCreated();

    Integer getNewCallSegment();

    CallingPartyNumberCap getCallingPartyNumber();

    CallReferenceNumber getCallReferenceNumber();

    ISDNAddressString getGsmSCFAddress();

    boolean getSuppressTCsi();

}