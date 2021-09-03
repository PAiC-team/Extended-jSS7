
package org.restcomm.protocols.ss7.map.api.service.mobility.imei;

import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
*
<code>
MAP_OBTAIN_IMEI service

Parameter name  Request Indication  Response    Confirm
Invoke id       M       M(=)        M(=)        M(=)
IMEI                                C           C(=)
User error                          C           C(=)
Provider error                                  O

If the service fails, the VLR sends the user error System Failure (see clause 7.6.1) to the MSC.
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface ObtainImeiRequest extends MobilityMessage {

}
