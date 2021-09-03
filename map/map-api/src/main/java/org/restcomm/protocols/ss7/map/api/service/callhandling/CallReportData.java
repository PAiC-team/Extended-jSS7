
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 CallReportData ::= SEQUENCE{ monitoringMode [0] MonitoringMode OPTIONAL, callOutcome [1] CallOutcome OPTIONAL,
 * extensionContainer [2] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallReportData extends Serializable {

    MonitoringMode getMonitoringMode();

    CallOutcome getCallOutcome();

    MAPExtensionContainer getExtensionContainer();

}
