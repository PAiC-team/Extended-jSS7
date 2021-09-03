
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.LMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3:
 *
 * setReportingState OPERATION ::= { --Timer m ARGUMENT SetReportingStateArg RESULT SetReportingStateRes -- optional ERRORS {
 * systemFailure | unidentifiedSubscriber | unexpectedDataValue | dataMissing | resourceLimitation | facilityNotSupported} CODE
 * local:73 }
 *
 * SetReportingStateArg ::= SEQUENCE { imsi [0] IMSI OPTIONAL, lmsi [1] LMSI OPTIONAL, ccbs-Monitoring [2] ReportingState
 * OPTIONAL, extensionContainer [3] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SetReportingStateRequest extends CallHandlingMessage {

    IMSI getImsi();

    LMSI getLmsi();

    ReportingState getCcbsMonitoring();

    MAPExtensionContainer getExtensionContainer();

}
