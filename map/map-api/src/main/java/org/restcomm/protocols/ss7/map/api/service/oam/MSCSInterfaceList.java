
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
MSC-S-InterfaceList ::= BIT STRING {
  a (0),
  iu (1),
  mc (2),
  map-g (3),
  map-b (4),
  map-e (5),
  map-f (6),
  cap (7),
  map-d (8),
  map-c (9)
} (SIZE (10..16))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MSCSInterfaceList extends Serializable {

    boolean getA();

    boolean getIu();

    boolean getMc();

    boolean getMapG();

    boolean getMapB();

    boolean getMapE();

    boolean getMapF();

    boolean getCap();

    boolean getMapD();

    boolean getMapC();

}
