
package org.restcomm.protocols.ss7.map.api.service.oam;

import org.restcomm.protocols.ss7.map.api.MAPMessage;
import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
MAP V3:
ActivateTraceModeRes ::= SEQUENCE {
  extensionContainer     [0] ExtensionContainer OPTIONAL,
  ...,
  traceSupportIndicator  [1] NULL OPTIONAL
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface ActivateTraceModeResponse_Base extends MAPMessage{

    MAPExtensionContainer getExtensionContainer();

    boolean getTraceSupportIndicator();

}
