
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CollectedInfo;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InformationToSend;

/**
 *
<code>
promptAndCollectUserInformation {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT PromptAndCollectUserInformationArg {bound}
  RESULT ReceivedInformationArg {bound}
  ERRORS {canceled | improperCallerResponse | missingParameter | parameterOutOfRange | systemFailure | taskRefused |
          unexpectedComponentSequence | unavailableResource | unexpectedDataValue | unexpectedParameter | unknownCSID}
  LINKED {specializedResourceReport} CODE opcode-promptAndCollectUserInformation
}
  -- Direction: gsmSCF -> gsmSRF, Timer: T pc
  -- This operation is used to interact with a user to collect information.

PromptAndCollectUserInformationArg {PARAMETERS-BOUND : bound}::= SEQUENCE {
  collectedInfo              [0] CollectedInfo,
  disconnectFromIPForbidden  [1] BOOLEAN DEFAULT TRUE,
  informationToSend          [2] InformationToSend {bound} OPTIONAL,
  extensions                 [3] Extensions {bound} OPTIONAL,
  callSegmentID              [4] CallSegmentID {bound} OPTIONAL,
  requestAnnouncementStartedNotification [51] BOOLEAN DEFAULT FALSE,
  ...
}

CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..bound.&numOfCSs)
numOfCSs ::= 127
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PromptAndCollectUserInformationRequest extends CircuitSwitchedCallMessage {

    CollectedInfo getCollectedInfo();

    Boolean getDisconnectFromIPForbidden();

    InformationToSend getInformationToSend();

    CAPExtensions getExtensions();

    Integer getCallSegmentID();

    Boolean getRequestAnnouncementStartedNotification();

}
