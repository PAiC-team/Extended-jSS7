
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
SGW-InterfaceList ::= BIT STRING {
  s4 (0),
  s5 (1),
  s8b (2),
  s11 (3),
  gxc (4)
} (SIZE (5..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SGWInterfaceList extends Serializable {

    boolean getS4();

    boolean getS5();

    boolean getS8b();

    boolean getS11();

    boolean getGxc();

}
