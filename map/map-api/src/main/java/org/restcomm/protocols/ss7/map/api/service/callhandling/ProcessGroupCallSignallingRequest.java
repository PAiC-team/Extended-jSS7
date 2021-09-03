
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.AccessNetworkSignalInfo;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AdditionalInfo;

/**
 *
 MAP V3:
 *
 * processGroupCallSignalling OPERATION ::= { --Timer s ARGUMENT ProcessGroupCallSignallingArg CODE local:41 }
 *
 * ProcessGroupCallSignallingArg ::= SEQUENCE { uplinkRequest [0] NULL OPTIONAL, uplinkReleaseIndication [1] NULL OPTIONAL,
 * releaseGroupCall [2] NULL OPTIONAL, extensionContainer ExtensionContainer OPTIONAL, ..., talkerPriority [3] TalkerPriority
 * OPTIONAL, additionalInfo [4] AdditionalInfo OPTIONAL, emergencyModeResetCommandFlag [5] NULL OPTIONAL, an-APDU [6]
 * AccessNetworkSignalInfo OPTIONAL }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ProcessGroupCallSignallingRequest extends CallHandlingMessage {

     boolean getUplinkRequest();

     boolean getUplinkReleaseIndication();

     boolean getReleaseGroupCall();

     MAPExtensionContainer getExtensionContainer();

     TalkerPriority getTalkerPriority();

     AdditionalInfo getAdditionalInfo();

     boolean getEmergencyModeResetCommandFlag();

     AccessNetworkSignalInfo getAnAPDU();

}
