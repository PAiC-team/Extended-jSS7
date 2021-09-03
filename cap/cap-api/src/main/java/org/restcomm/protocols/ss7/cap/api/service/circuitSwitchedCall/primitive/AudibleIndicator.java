
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.BurstList;

/**
 *
<code>
AudibleIndicator ::= CHOICE {
  tone       BOOLEAN,
  burstList  [1] BurstList
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AudibleIndicator extends Serializable {

    Boolean getTone();

    BurstList getBurstList();

}