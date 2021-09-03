
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.EventTypeBCSM;
import org.restcomm.protocols.ss7.cap.api.primitives.ReceivingSideID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.EventSpecificInformationBCSM;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;

/**
<code>
The event report BCSM (ERB) operation is used in combination with CAP Request Report BCSM (RRB).
It is used to report the occurrence of an event, in the case that the event was previously armed.
The mode in which the event is reported (EDP-N, EDP-R) depends on the arming state of the event.
See also the description of request report BCSM {@link RequestReportBCSMEventRequest}


eventReportBCSM {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT EventReportBCSMArg {bound}
  RETURN RESULT FALSE
  ALWAYS RESPONDS FALSE
  CODE opcode-eventReportBCSM
}
-- Direction: gsmSSF -> gsmSCF, Timer: Terb
-- This operation is used to notify the gsmSCF of a call-related event (e.g. BCSM
-- events such as O_Busy or O_No_Answer) previously requested by the gsmSCF in a
-- RequestReportBCSMEvent operation.

EventReportBCSMArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  eventTypeBCSM                 [0] EventTypeBCSM,
  eventSpecificInformationBCSM  [2] EventSpecificInformationBCSM {bound} OPTIONAL,
  legID                         [3] ReceivingSideID OPTIONAL,
  miscCallInfo                  [4] MiscCallInfo DEFAULT {messageType request},
  extensions                    [5] Extensions {bound} OPTIONAL,
  ...
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface EventReportBCSMRequest extends CircuitSwitchedCallMessage {

    EventTypeBCSM getEventTypeBCSM();

    EventSpecificInformationBCSM getEventSpecificInformationBCSM();

    ReceivingSideID getLegID();

    MiscCallInfo getMiscCallInfo();

    CAPExtensions getExtensions();

}
