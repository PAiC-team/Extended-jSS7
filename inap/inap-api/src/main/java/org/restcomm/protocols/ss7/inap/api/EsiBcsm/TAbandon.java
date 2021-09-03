package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.CauseInap;

/**
*
<code>
  tAbandon [22] SEQUENCE {
    abandonCause [0] Cause {bound} OPTIONAL,
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface TAbandon extends Serializable {

    CauseInap getAbandonCause();

}
