
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
PGW-EventList ::= BIT STRING {
  pdn-connectionCreation (0),
  pdn-connectionTermination (1),
  bearerActivationModificationDeletion (2)
} (SIZE (3..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PGWEventList extends Serializable {

    boolean getPdnConnectionCreation();

    boolean getPdnConnectionTermination();

    boolean getBearerActivationModificationDeletion();

}
