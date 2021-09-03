
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 * MAP V3:
 *
 *
<code>
ist-Command OPERATION::= {
  --Timer m
  ARGUMENT IST-CommandArg
  RESULT IST-CommandRes -- optional
  ERRORS { unexpectedDataValue | resourceLimitation | unknownSubscriber | systemFailure | facilityNotSupported}
  CODE local:88
}

IST-CommandArg ::= SEQUENCE{
  imsi                [0] IMSI,
  extensionContainer  [1] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface IstCommandRequest extends CallHandlingMessage {

     IMSI getImsi();

     MAPExtensionContainer getExtensionContainer();

}
