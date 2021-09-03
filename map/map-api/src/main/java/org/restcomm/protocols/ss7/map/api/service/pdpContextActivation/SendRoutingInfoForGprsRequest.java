
package org.restcomm.protocols.ss7.map.api.service.pdpContextActivation;

import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressString;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
MAP V3-4:

sendRoutingInfoForGprs OPERATION ::= {
  --Timer m
  ARGUMENT SendRoutingInfoForGprsArg
  RESULT SendRoutingInfoForGprsRes
  ERRORS { absentSubscriber | systemFailure | dataMissing | unexpectedDataValue | unknownSubscriber | callBarred }
  CODE local:24
}

SendRoutingInfoForGprsArg ::= SEQUENCE {
  imsi                [0] IMSI,
  ggsn-Address        [1] GSN-Address OPTIONAL,
  ggsn-Number         [2] ISDN-AddressString,
  extensionContainer  [3] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendRoutingInfoForGprsRequest extends PdpContextActivationMessage {

    IMSI getImsi();

    GSNAddress getGgsnAddress();

    ISDNAddressString getGgsnNumber();

    MAPExtensionContainer getExtensionContainer();

}
