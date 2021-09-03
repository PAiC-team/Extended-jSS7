
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CUGInterlock;

/**
 *
 CUG-CheckInfo ::= SEQUENCE { cug-Interlock CUG-Interlock, cug-OutgoingAccess NULL OPTIONAL, extensionContainer
 * ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author cristian veliscu
 *
 */
public interface CUGCheckInfo extends Serializable {

     CUGInterlock getCUGInterlock();

     boolean getCUGOutgoingAccess();

     MAPExtensionContainer getExtensionContainer();

}