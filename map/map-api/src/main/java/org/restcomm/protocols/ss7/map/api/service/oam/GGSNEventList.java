
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
GGSN-EventList ::= BIT STRING {
  pdpContext (0),
  mbmsContext (1)
} (SIZE (2..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GGSNEventList extends Serializable {

    boolean getPdpContext();

    boolean getMbmsContext();

}
