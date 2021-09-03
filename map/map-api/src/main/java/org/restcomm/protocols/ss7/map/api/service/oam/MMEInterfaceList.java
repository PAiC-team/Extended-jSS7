
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
MME-InterfaceList ::= BIT STRING {
  s1-mme (0),
  s3 (1),
  s6a (2),
  s10 (3),
  s11 (4)
} (SIZE (5..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MMEInterfaceList extends Serializable {

    boolean getS1Mme();

    boolean getS3();

    boolean getS6a();

    boolean getS10();

    boolean getS11();

}
