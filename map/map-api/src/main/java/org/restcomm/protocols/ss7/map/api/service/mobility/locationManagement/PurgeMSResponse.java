
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V3: PurgeMS-Res ::= SEQUENCE {
   freezeTMSI         [0] NULL OPTIONAL,
   freezeP-TMSI       [1] NULL OPTIONAL,
   extensionContainer ExtensionContainer OPTIONAL,
   ...,
   freezeM-TMSI       [2] NULL OPTIONAL
 }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PurgeMSResponse extends MobilityMessage {

    boolean getFreezeTMSI();

    boolean getFreezePTMSI();

    MAPExtensionContainer getExtensionContainer();

    boolean getFreezeMTMSI();

}
