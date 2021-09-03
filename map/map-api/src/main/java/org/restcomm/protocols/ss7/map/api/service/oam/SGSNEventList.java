
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
SGSN-EventList ::= BIT STRING {
  pdpContext (0),
  mo-mt-sms (1),
  rau-gprsAttach-gprsDetach (2),
  mbmsContext (3)
} (SIZE (4..16))
-- Other bits than listed above shall be discarded.
<code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SGSNEventList extends Serializable {

    boolean getPdpContext();

    boolean getMoMtSms();

    boolean getRauGprsAttachGprsDetach();

    boolean getMbmsContext();

}
