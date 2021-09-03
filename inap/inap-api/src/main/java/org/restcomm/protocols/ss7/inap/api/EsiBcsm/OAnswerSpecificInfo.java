package org.restcomm.protocols.ss7.inap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.BackwardGVNSInap;

/**
*
<code>
  oAnswerSpecificInfo [5] SEQUENCE {
  backwardGVNS [0] BackwardGVNS {bound} OPTIONAL,
    ...
  },
</code>
*
* @author sergey vetyutnev
*
*/
public interface OAnswerSpecificInfo extends Serializable {

    BackwardGVNSInap getBackwardGVNS();

}
