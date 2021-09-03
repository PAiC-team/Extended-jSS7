
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CSGId;

/**
 *
<code>
UserCSGInformation ::= SEQUENCE {
  csg-Id               [0] CSG-Id,
  extensionContainer   [1] ExtensionContainer OPTIONAL,
  ...,
  accessMode           [2] OCTET STRING (SIZE(1)) OPTIONAL,
  cmi                  [3] OCTET STRING (SIZE(1)) OPTIONAL
}
-- The encoding of the accessMode and cmi parameters are as defined in 3GPP TS 29.060 [105].
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface UserCSGInformation extends Serializable {

    CSGId getCSGId();

    MAPExtensionContainer getExtensionContainer();

    Integer getAccessMode();

    Integer getCmi();

}
