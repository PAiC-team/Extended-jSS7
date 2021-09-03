package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

/**
*
<code>
  tBusySpecificInfo [8] SEQUENCE {
    busyCause [0] Cause {bound} OPTIONAL,
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface TBusySpecificInfo extends Serializable {

}
