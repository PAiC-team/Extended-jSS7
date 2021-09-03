
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 AdditionalSubscriptions ::= BIT STRING { privilegedUplinkRequest (0), emergencyUplinkRequest (1), emergencyReset (2)} (SIZE
 * (3..8)) -- Other bits than listed above shall be discarded.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AdditionalSubscriptions extends Serializable {

    boolean getPrivilegedUplinkRequest();

    boolean getEmergencyUplinkRequest();

    boolean getEmergencyReset();

}
