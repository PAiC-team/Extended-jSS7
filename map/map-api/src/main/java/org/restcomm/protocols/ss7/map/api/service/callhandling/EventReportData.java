
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 EventReportData ::= SEQUENCE{ ccbs-SubscriberStatus [0] CCBS-SubscriberStatus OPTIONAL, extensionContainer [1]
 * ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EventReportData extends Serializable {

     CCBSSubscriberStatus getCcbsSubscriberStatus();

     MAPExtensionContainer getExtensionContainer();

}
