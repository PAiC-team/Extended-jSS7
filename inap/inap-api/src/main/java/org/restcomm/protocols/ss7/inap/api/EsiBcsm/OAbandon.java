package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.CauseInap;

/**
*
<code>
  oAbandon [21] SEQUENCE {
    abandonCause [0] Cause {bound} OPTIONAL,
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface OAbandon extends Serializable {

    CauseInap getAbandonCause();

}
