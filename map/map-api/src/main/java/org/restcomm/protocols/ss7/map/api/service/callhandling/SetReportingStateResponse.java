
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 SetReportingStateRes ::= SEQUENCE{ ccbs-SubscriberStatus [0] CCBS-SubscriberStatus OPTIONAL, extensionContainer [1]
 * ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SetReportingStateResponse extends CallHandlingMessage {

    CCBSSubscriberStatus getCcbsSubscriberStatus();

    MAPExtensionContainer getExtensionContainer();

}
