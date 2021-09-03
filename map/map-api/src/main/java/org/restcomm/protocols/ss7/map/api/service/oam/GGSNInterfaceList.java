
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
GGSN-InterfaceList ::= BIT STRING {
  gn (0),
  gi (1),
  gmb (2)
} (SIZE (3..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GGSNInterfaceList extends Serializable {

    boolean getGn();

    boolean getGi();

    boolean getGmb();

}
