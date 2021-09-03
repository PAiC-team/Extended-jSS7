
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
SGSN-InterfaceList ::= BIT STRING {
  gb (0),
  iu (1),
  gn (2),
  map-gr (3),
  map-gd (4),
  map-gf (5),
  gs (6),
  ge (7),
  s3 (8),
  s4 (9),
  s6d (10)
} (SIZE (8..16))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SGSNInterfaceList extends Serializable {

    boolean getGb();

    boolean getIu();

    boolean getGn();

    boolean getMapGr();

    boolean getMapGd();

    boolean getMapGf();

    boolean getGs();

    boolean getGe();

    boolean getS3();

    boolean getS4();

    boolean getS6d();

}
