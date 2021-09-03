
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
MSC-S-EventList ::= BIT STRING {
  mo-mtCall (0),
  mo-mt-sms (1),
  lu-imsiAttach-imsiDetach (2),
  handovers (3),
  ss (4)
} (SIZE (5..16))
-- Other bits than listed above shall be discarded.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MSCSEventList extends Serializable {

    boolean getMoMtCall();

    boolean getMoMtSms();

    boolean getLuImsiAttachImsiDetach();

    boolean getHandovers();

    boolean getSs();

}
