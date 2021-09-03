
package org.restcomm.protocols.ss7.map.api.service.mobility.pagingAndSearch;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.primitives.LAIFixedLength;
import org.restcomm.protocols.ss7.map.api.primitives.TMSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
*
<code>
MAP_PAGE service

Parameter name            Request Indication  Response    Confirm
Invoke Id                 M       M(=)        M(=)        M(=)
IMSI                      M       M(=)
Stored location area Id   M       M(=)
TMSI                      U       C(=)
User error                                    C           C(=)
Provider error                                            O

The following error causes defined in clause 7.6.1 may be sent by the user in case of a paging error, depending on the failure reason:
-   absent subscriber;
-   unknown location area;
-   busy subscriber;
-   system failure;
-   this corresponds to the case where there is no call associated with the MAP_PAGE service, i.e. if the call has been released but the dialogue to the VLR has not been aborted;
-   unexpected data value.
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface PageRequest extends MobilityMessage {

    IMSI getImsi();

    LAIFixedLength getStoredLAI();

    TMSI getTmsi();

}
