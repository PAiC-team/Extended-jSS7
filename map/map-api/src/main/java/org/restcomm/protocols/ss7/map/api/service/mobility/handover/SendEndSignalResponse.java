
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V3: SendEndSignal-Res ::= SEQUENCE { extensionContainer [0] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendEndSignalResponse extends MobilityMessage {

    MAPExtensionContainer getExtensionContainer();

}
