
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3:
 *
 * statusReport OPERATION ::= { --Timer m ARGUMENT StatusReportArg RESULT StatusReportRes -- optional ERRORS { unknownSubscriber
 * | systemFailure | unexpectedDataValue | dataMissing} CODE local:74 }
 *
 * StatusReportArg ::= SEQUENCE{ imsi [0] IMSI, eventReportData [1] EventReportData OPTIONAL, callReportdata [2] CallReportData
 * OPTIONAL, extensionContainer [3] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface StatusReportRequest extends CallHandlingMessage {

    IMSI getImsi();

    EventReportData getEventReportData();

    CallReportData getCallReportData();

    MAPExtensionContainer getExtensionContainer();

}
