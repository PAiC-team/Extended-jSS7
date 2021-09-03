
package org.restcomm.protocols.ss7.map.api.service.mobility.imei;

import org.restcomm.protocols.ss7.map.api.primitives.IMEI;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
*
<code>
Parameter name  Request Indication  Response    Confirm
IMEI                                C           C(=)
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface ObtainImeiResponse extends MobilityMessage {

    IMEI getImei();

}
