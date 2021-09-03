package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.inap.api.primitives.EventTypeBCSM;
import org.restcomm.protocols.ss7.inap.api.primitives.INAPExtensions;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;
import org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive.EventSpecificInformationBCSM;

/**
*
<code>
*** CS1: ***
EventReportBCSM ::= OPERATION
ARGUMENT EventReportBCSMArg
-- Direction: SSF -> SCF, Timer: Terb
-- This operation is used to notify the SCF of a call-related event (e.g., BCSM events such as
-- busy or no answer) previously requested by the SCF in a RequestReportBCSMEvent operation.

*** CS2: ***
eventReportBCSM {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT EventReportBCSMArg {bound}
  RETURN RESULT FALSE
  ALWAYS RESPONDS FALSE
  CODE opcode-eventReportBCSM
}
-- Direction: SSF -> SCF, Timer: Terb
-- This operation is used to notify the SCF of a call-related event (e.g. BCSM events such as busy
-- or no answer) previously requested by the SCF in a RequestReportBCSMEvent operation.

EventReportBCSMArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  eventTypeBCSM                [0] EventTypeBCSM,
  eventSpecificInformationBCSM [2] EventSpecificInformationBCSM {bound} OPTIONAL,
  legID                        [3] LegID OPTIONAL,
  miscCallInfo                 [4] MiscCallInfo DEFAULT {messageType request},
  extensions                   [5] SEQUENCE SIZE(1..bound.&numOfExtensions) OF ExtensionField {bound} OPTIONAL,
  ...
}
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface EventReportBCSMRequest {

    EventTypeBCSM getEventTypeBCSM();

    EventSpecificInformationBCSM getEventSpecificInformationBCSM();

    LegID getLegID();

    MiscCallInfo getMiscCallInfo();

    INAPExtensions getExtensions();

}
