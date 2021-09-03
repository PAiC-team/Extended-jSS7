
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
UU-Data ::= SEQUENCE {
  uuIndicator         [0] UUIndicator OPTIONAL,
  uui                 [1] UUI OPTIONAL,
  uusCFInteraction    [2] NULL OPTIONAL,
  extensionContainer  [3] ExtensionContainer OPTIONAL,
  ...
}

UUIndicator ::= OCTET STRING (SIZE (1))
-- Octets are coded according to ETS 300 356
UUI  ::= OCTET STRING (SIZE (1..131))
-- Octets are coded according to ETS 300 356

</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface UUData extends Serializable {

    UUIndicator getUUIndicator();

    UUI getUUI();

    boolean getUusCFInteraction();

    MAPExtensionContainer getExtensionContainer();

}
