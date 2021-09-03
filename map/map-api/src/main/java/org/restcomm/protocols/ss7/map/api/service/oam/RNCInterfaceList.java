
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
RNC-InterfaceList ::= BIT STRING {
  iu (0),
  iur (1),
  iub (2),
  uu (3)
} (SIZE (4..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RNCInterfaceList extends Serializable {

    boolean getIu();

    boolean getIur();

    boolean getIub();

    boolean getUu();

}
