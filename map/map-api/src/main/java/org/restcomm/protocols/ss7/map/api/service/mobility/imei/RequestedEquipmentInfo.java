
package org.restcomm.protocols.ss7.map.api.service.mobility.imei;

import java.io.Serializable;

/**
 * RequestedEquipmentInfo::= BIT STRING { equipmentStatus (0), bmuef (1)} (SIZE (2..8)) -- exception handling: reception of
 * unknown bit assignments in the -- RequestedEquipmentInfo data type shall be discarded by the receiver
 *
 * @author normandes
 *
 */
public interface RequestedEquipmentInfo extends Serializable {

    boolean getEquipmentStatus();

    boolean getBmuef();

}
