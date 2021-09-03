
package org.restcomm.protocols.ss7.cap.api.EsiBcsm;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.Digits;

/**
 *
<code>
midCallEvents [1] CHOICE {
  dTMFDigitsCompleted [3] Digits {bound},
  dTMFDigitsTimeOut   [4] Digits {bound}
} OPTIONAL,
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MidCallEvents extends Serializable {

    Digits getDTMFDigitsCompleted();

    Digits getDTMFDigitsTimeOut();

}
