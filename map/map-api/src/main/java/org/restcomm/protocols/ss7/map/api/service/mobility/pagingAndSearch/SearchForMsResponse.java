
package org.restcomm.protocols.ss7.map.api.service.mobility.pagingAndSearch;

import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
*
<code>
Parameter name             Request Indication  Response    Confirm
Current location area Id                       C           C(=)
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface SearchForMsResponse extends MobilityMessage {

    LAIFixedLength getCurrentLAI();

}
