
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventSpecificInformation;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.GPRSEventType;
import org.restcomm.protocols.ss7.cap.api.service.gprs.primitive.PDPID;
import org.restcomm.protocols.ss7.inap.api.primitives.MiscCallInfo;

/**
 *
 eventReportGPRS {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT EventReportGPRSArg {bound} RETURN RESULT TRUE ERRORS
 * {unknownPDPID} CODE opcode-eventReportGPRS} -- Direction gprsSSF -> gsmSCF,Timer Tereg -- This operation is used to notify
 * the gsmSCF of a GPRS session or PDP context related -- events (e.g. PDP context activation) previously requested by the
 * gsmSCF in a -- RequestReportGPRSEventoperation.
 *
 * EventReportGPRSArg {PARAMETERS-BOUND : bound}::= SEQUENCE { gPRSEventType [0] GPRSEventType, miscGPRSInfo [1] MiscCallInfo
 * DEFAULT {messageType request}, gPRSEventSpecificInformation [2] GPRSEventSpecificInformation {bound} OPTIONAL, pDPID [3]
 * PDPID OPTIONAL, ... }
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EventReportGPRSRequest extends GprsMessage {

    GPRSEventType getGPRSEventType();

    MiscCallInfo getMiscGPRSInfo();

    GPRSEventSpecificInformation getGPRSEventSpecificInformation();

    PDPID getPDPID();

}