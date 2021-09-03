
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.AdditionalInfo;

/**
 *
 MAP V3:
 *
 * sendGroupCallEndSignal OPERATION ::= { --Timer l ARGUMENT SendGroupCallEndSignalArg RESULT SendGroupCallEndSignalRes CODE
 * local:40 }
 *
 * SendGroupCallEndSignalArg ::= SEQUENCE { imsi IMSI OPTIONAL, extensionContainer ExtensionContainer OPTIONAL, ...,
 * talkerPriority [0]TalkerPriority OPTIONAL, additionalInfo [1]AdditionalInfo OPTIONAL }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendGroupCallEndSignalRequest extends CallHandlingMessage {

     IMSI getImsi();

     MAPExtensionContainer getExtensionContainer();

     TalkerPriority getTalkerPriority();

     AdditionalInfo getAdditionalInfo();

}
