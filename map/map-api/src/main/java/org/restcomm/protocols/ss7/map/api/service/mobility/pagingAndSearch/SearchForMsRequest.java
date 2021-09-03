
package org.restcomm.protocols.ss7.map.api.service.mobility.pagingAndSearch;

import org.restcomm.protocols.ss7.map.api.primitives.IMSI;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
*
<code>
MAP_SEARCH_FOR_MS service

Parameter name             Request Indication  Response    Confirm
Invoke Id                  M       M(=)        M(=)        M(=)
IMSI                       M       M(=)
Current location area Id                       C           C(=)
User error                                     C           C(=)
Provider error                                             O

The following error causes defined in clause 7.6.1 shall be sent by the user if the search procedure fails, depending on the failure reason:
-   absent subscriber;
    this error cause is returned by the MSC if the MS does not respond to the paging request;
-   system failure;
-   this corresponds to the case where there is no call associated with the MAP_SEARCH_FOR_MS service, i.e. if the call has been released but the dialogue to the VLR has not been aborted;
-   busy subscriber;
-   unexpected data value.
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface SearchForMsRequest extends MobilityMessage {

    IMSI getImsi();

}
