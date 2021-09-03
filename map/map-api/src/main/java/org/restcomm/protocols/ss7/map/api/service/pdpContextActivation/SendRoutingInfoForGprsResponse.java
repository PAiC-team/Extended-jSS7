
package org.restcomm.protocols.ss7.map.api.service.pdpContextActivation;

import org.restcomm.protocols.ss7.map.api.primitives.GSNAddress;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
SendRoutingInfoForGprsRes ::= SEQUENCE {
  sgsn-Address              [0] GSN-Address,
  ggsn-Address              [1] GSN-Address OPTIONAL,
  mobileNotReachableReason  [2] AbsentSubscriberDiagnosticSM OPTIONAL,
  extensionContainer        [3] ExtensionContainer OPTIONAL,
  ...
}

AbsentSubscriberDiagnosticSM ::= INTEGER (0..255)
-- AbsentSubscriberDiagnosticSM values are defined in 3GPP TS 23.040
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SendRoutingInfoForGprsResponse extends PdpContextActivationMessage {

    GSNAddress getSgsnAddress();

    GSNAddress getGgsnAddress();

    Integer getMobileNotReachableReason();

    MAPExtensionContainer getExtensionContainer();

}
