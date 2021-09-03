
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
ENB-InterfaceList ::= BIT STRING {
  s1-mme (0),
  x2 (1),
  uu (2)
} (SIZE (3..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ENBInterfaceList extends Serializable {

    boolean getS1Mme();

    boolean getX2();

    boolean getUu();

}
