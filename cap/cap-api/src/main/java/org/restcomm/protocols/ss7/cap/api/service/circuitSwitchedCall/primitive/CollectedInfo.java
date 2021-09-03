
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
CollectedInfo ::= CHOICE {
  collectedDigits [0] CollectedDigits
}
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CollectedInfo extends Serializable {

    CollectedDigits getCollectedDigits();

}
