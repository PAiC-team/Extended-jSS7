
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.cap.api.primitives.BCSMEvent;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;

/**
 *
<code>
requestReportBCSMEvent {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT RequestReportBCSMEventArg {bound}
  RETURN RESULT FALSE
  ERRORS {missingParameter | parameterOutOfRange | systemFailure | taskRefused | unexpectedComponentSequence |
          unexpectedDataValue | unexpectedParameter | unknownLegID}
  CODE opcode-requestReportBCSMEvent
}
-- Direction: gsmSCF -> gsmSSF, Timer: Trrb
-- This operation is used to request the gsmSSF to monitor for a call-related event
-- (e.g. BCSM events such as O_Busy or O_No_Answer) and to send a notification
-- to the gsmSCF when the event is detected.
-- -- NOTE:
-- Every EDP must be explicitly armed by the gsmSCF via a RequestReportBCSMEvent operation.
-- No implicit arming of EDPs at the gsmSSF after reception of any operation (different
-- from RequestReportBCSMEvent) from the gsmSCF is allowed.

RequestReportBCSMEventArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  bcsmEvents     [0] SEQUENCE SIZE(1..bound.&numOfBCSMEvents) OF BCSMEvent {bound},
  extensions     [2] Extensions {bound} OPTIONAL,
  ...
}
-- Indicates the BCSM related events for notification.

numOfBCSMEvents = 30
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RequestReportBCSMEventRequest extends CircuitSwitchedCallMessage {

    ArrayList<BCSMEvent> getBCSMEventList();

    CAPExtensions getExtensions();

}
