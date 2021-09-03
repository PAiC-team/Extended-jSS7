
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 *
<code>
UtranPositioningDataInfo ::= OCTET STRING (SIZE (3..11))
-- Refers to the Position Data defined in 3GPP TS 25.413.
-- This is composed of the positioningDataDiscriminator and the positioningDataSet
-- included in positionData as defined in 3GPP TS 25.413.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface UtranPositioningDataInfo extends Serializable {

    byte[] getData();

}
