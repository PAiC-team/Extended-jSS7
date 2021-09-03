
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
 MAP V3:
 *
 * ist-Alert OPERATION ::= { --Timer m ARGUMENT IST-AlertArg RESULT IST-AlertRes -- optional ERRORS { unexpectedDataValue |
 * resourceLimitation | unknownSubscriber | systemFailure | facilityNotSupported} CODE local:87 }
 *
 * IST-AlertArg ::= SEQUENCE{ imsi [0] IMSI, extensionContainer [1] ExtensionContainer OPTIONAL, ...}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface IstAlertRequest extends CallHandlingMessage {

     IMSI getImsi();

     MAPExtensionContainer getExtensionContainer();

}
