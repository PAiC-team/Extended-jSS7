
package org.restcomm.protocols.ss7.map.api.service.mobility.imei;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainer;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 MAP V3: CheckIMEIRes ::= SEQUENCE { equipmentStatus EquipmentStatus OPTIONAL, bmuef UESBI-Iu OPTIONAL, extensionContainer [0]
 * ExtensionContainer OPTIONAL, ...}
 *
 * MAP V2: RESULT equipmentStatus EquipmentStatus
 *
 *
 * @author normandes
 *
 */
public interface CheckImeiResponse extends MobilityMessage {

    EquipmentStatus getEquipmentStatus();

    UESBIIu getBmuef();

    MAPExtensionContainer getExtensionContainer();

}
