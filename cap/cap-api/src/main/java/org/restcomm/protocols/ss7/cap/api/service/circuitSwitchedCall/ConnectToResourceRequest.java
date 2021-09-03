
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.ServiceInteractionIndicatorsTwo;

/**
 *
<code>
connectToResource {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT ConnectToResourceArg {bound}
  RETURN RESULT FALSE
  ERRORS {missingParameter | systemFailure | taskRefused | unexpectedComponentSequence | unexpectedDataValue | unexpectedParameter | unknownCSID}
  CODE opcode-connectToResource
}
-- Direction: gsmSCF -> gsmSSF, Timer: T ctr
-- This operation is used to connect a call segment from the gsmSSF to the
-- gsmSRF.

ConnectToResourceArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  resourceAddress CHOICE {
    ipRoutingAddress  [0] IPRoutingAddress {bound},
    none              [3] NULL
  },
  extensions                        [4] Extensions {bound} OPTIONAL,
  serviceInteractionIndicatorsTwo   [7] ServiceInteractionIndicatorsTwo OPTIONAL,
  callSegmentID                     [50] CallSegmentID {bound} OPTIONAL,
  ...
}

IPRoutingAddress {PARAMETERS-BOUND : bound} ::= CalledPartyNumber {bound}
-- Indicates the routing address for the IP.

CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..bound.&numOfCSs)
numOfCSs ::= 127
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ConnectToResourceRequest extends CircuitSwitchedCallMessage {

    CalledPartyNumberCap getResourceAddress_IPRoutingAddress();

    boolean getResourceAddress_Null();

    CAPExtensions getExtensions();

    ServiceInteractionIndicatorsTwo getServiceInteractionIndicatorsTwo();

    Integer getCallSegmentID();

}