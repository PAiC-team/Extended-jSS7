
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.OfferedCamel4CSIs;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SupportedCamelPhases;

/**
 * CamelInfo ::= SEQUENCE { supportedCamelPhases SupportedCamelPhases, suppress-T-CSI NULL OPTIONAL, extensionContainer
 * ExtensionContainer OPTIONAL, ... , offeredCamel4CSIs [0] OfferedCamel4CSIs OPTIONAL }
 *
 * @author cristian veliscu
 *
 */
public interface CamelInfo extends Serializable {

    SupportedCamelPhases getSupportedCamelPhases();

    boolean getSuppressTCSI();

    MAPExtensionContainer getExtensionContainer();

    OfferedCamel4CSIs getOfferedCamel4CSIs();
}
