
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;

/**
 *
<code>
ModificationRequestFor-IP-SM-GW-Data ::= SEQUENCE {
  modifyRegistrationStatus   [0] ModificationInstruction OPTIONAL,
  extensionContainer         [1] ExtensionContainer OPTIONAL,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ModificationRequestForIPSMGWData extends Serializable {

    ModificationInstruction getModifyRegistrationStatus();

    MAPExtensionContainer getExtensionContainer();

}
