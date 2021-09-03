
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
MME-EventList ::= BIT STRING {
  ue-initiatedPDNconectivityRequest (0),
  serviceRequestts (1),
  initialAttachTrackingAreaUpdateDetach (2),
  ue-initiatedPDNdisconnection (3),
  bearerActivationModificationDeletion (4),
  handover (5)
} (SIZE (6..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MMEEventList extends Serializable {

    boolean getUeInitiatedPDNconectivityRequest();

    boolean getServiceRequests();

    boolean getInitialAttachTrackingAreaUpdateDetach();

    boolean getUeInitiatedPDNdisconnection();

    boolean getBearerActivationModificationDeletion();

    boolean getHandover();

}
