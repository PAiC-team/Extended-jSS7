
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
MGW-EventList ::= BIT STRING {
  context (0)
} (SIZE (1..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MGWEventList extends Serializable {

    boolean getContext();

}
