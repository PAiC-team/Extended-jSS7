
package org.restcomm.protocols.ss7.cap.api.gap;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.InformationToSend;

/**
 *
<code>
GapTreatment {PARAMETERS-BOUND : bound} ::= CHOICE {
  informationToSend [0] InformationToSend {bound},
  releaseCause      [1] Cause {bound}
}
-- The default value for Cause is the same as in ISUP.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GapTreatment extends Serializable {

    InformationToSend getInformationToSend();

    CauseCap getCause();

}