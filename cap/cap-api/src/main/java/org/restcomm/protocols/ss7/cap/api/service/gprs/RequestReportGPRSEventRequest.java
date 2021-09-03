
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEvent;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;

/**
 *
 requestReportGPRSEvent {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT RequestReportGPRSEventArg {bound} RETURN RESULT
 * FALSE ERRORS {missingParameter | parameterOutOfRange | systemFailure | taskRefused | unexpectedComponentSequence |
 * unexpectedDataValue | unexpectedParameter | unknownPDPID} CODE opcode-requestReportGPRSEvent} -- Direction: gsmSCF ->
 * gprsSSF, Timer: Trrqe -- This operation is used to request the gprsSSF to monitor for an event (e.g., GPRS events -- such as
 * attach or PDP Context activiation), then send a notification back to the -- gsmSCF when the event is detected.
 *
 * RequestReportGPRSEventArg {PARAMETERS-BOUND : bound} ::= SEQUENCE { gPRSEvent [0] SEQUENCE SIZE (1..10) OF GPRSEvent, pDPID
 * [1] PDPID OPTIONAL, ... } -- Indicates the GPRS related events for notification.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RequestReportGPRSEventRequest extends GprsMessage {

    ArrayList<GPRSEvent> getGPRSEvent();

    PDPID getPDPID();

}