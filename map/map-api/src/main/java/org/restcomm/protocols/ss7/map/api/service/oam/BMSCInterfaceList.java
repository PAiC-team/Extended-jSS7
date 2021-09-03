
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
BMSC-InterfaceList ::= BIT STRING {
  gmb (0)
} (SIZE (1..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface BMSCInterfaceList extends Serializable {

    boolean getGmb();

}
