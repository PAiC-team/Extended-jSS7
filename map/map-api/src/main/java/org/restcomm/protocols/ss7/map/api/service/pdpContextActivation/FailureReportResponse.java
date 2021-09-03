
package org.restcomm.protocols.ss7.map.api.service.pdpContextActivation;

import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 FailureReportRes ::= SEQUENCE { ggsn-Address [0] GSN-Address OPTIONAL, extensionContainer [1] ExtensionContainer OPTIONAL,
 * ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface FailureReportResponse extends PdpContextActivationMessage {

    GSNAddress getGgsnAddress();

    MAPExtensionContainer getExtensionContainer();

}
