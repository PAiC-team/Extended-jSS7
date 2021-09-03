
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 *
 UtranGANSSpositioningData ::= OCTET STRING (SIZE (1..9)) -- Refers to the Position Data defined in 3GPP TS 25.413. -- This is
 * composed of the GANSS-PositioningDataSet only, included in PositionData -- as defined in 3GPP TS 25.413.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface UtranGANSSpositioningData extends Serializable {

    byte[] getData();

}
