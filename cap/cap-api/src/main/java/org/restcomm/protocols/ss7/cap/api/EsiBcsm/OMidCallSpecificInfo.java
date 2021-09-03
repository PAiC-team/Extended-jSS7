
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

/**
 *
<code>
oMidCallSpecificInfo [6] SEQUENCE {
  midCallEvents [1] CHOICE {
    dTMFDigitsCompleted  [3] Digits {bound},
    dTMFDigitsTimeOut    [4] Digits {bound}
  } OPTIONAL,
  ...
},
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface OMidCallSpecificInfo extends Serializable {

    MidCallEvents getMidCallEvents();

}
