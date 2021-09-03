
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 IST-AlertRes ::= SEQUENCE{ istAlertTimer [0] IST-AlertTimerValue OPTIONAL, istInformationWithdraw [1] NULL OPTIONAL,
 * callTerminationIndicator [2] CallTerminationIndicator OPTIONAL, extensionContainer [3] ExtensionContainer OPTIONAL, ...}
 *
 * IST-AlertTimerValue ::= INTEGER (15..255)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface IstAlertResponse extends CallHandlingMessage {

     Integer getIstAlertTimer();

     boolean getIstInformationWithdraw();

     CallTerminationIndicator getCallTerminationIndicator();

     MAPExtensionContainer getExtensionContainer();

}
