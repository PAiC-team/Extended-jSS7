
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 GERAN-Classmark ::= OCTET STRING (SIZE (2..87)) -- Octets are coded according the GERAN Classmark information element in 3GPP
 * TS 48.008
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GERANClassmark extends Serializable {

    byte[] getData();

}
