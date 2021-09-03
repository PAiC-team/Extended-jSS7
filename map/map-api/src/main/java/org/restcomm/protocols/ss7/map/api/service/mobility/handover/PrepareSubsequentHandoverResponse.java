
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import org.restcomm.protocols.ss7.map.api.primitives.AccessNetworkSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.ExternalSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V3: PrepareSubsequentHO-Res ::= [3] SEQUENCE { an-APDU AccessNetworkSignalInfo, extensionContainer [0] ExtensionContainer
 * OPTIONAL, ...}
 *
 * MAP V2: RESULT bss-APDU ExternalSignalInfo
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PrepareSubsequentHandoverResponse extends MobilityMessage {

    AccessNetworkSignalInfo getAnAPDU();

    MAPExtensionContainer getExtensionContainer();

    // MAP V2
    ExternalSignalInfo getBssAPDU();

}
