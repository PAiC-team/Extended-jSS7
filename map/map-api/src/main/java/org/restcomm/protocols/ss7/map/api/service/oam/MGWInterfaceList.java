
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
MGW-InterfaceList ::= BIT STRING {
  mc (0),
  nb-up (1),
  iu-up (2)
} (SIZE (3..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MGWInterfaceList extends Serializable {

    boolean getMc();

    boolean getNbUp();

    boolean getIuUp();

}
