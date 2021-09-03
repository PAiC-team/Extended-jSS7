
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
 *
<code>
ServingNode ::= BIT STRING {
  mme (0),
  sgsn (1)
} (SIZE (2..8))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ServingNode extends Serializable {

    boolean getMme();

    boolean getSgsn();

}
