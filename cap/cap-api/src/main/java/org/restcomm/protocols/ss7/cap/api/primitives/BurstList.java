
package org.restcomm.protocols.ss7.cap.api.primitives;

import java.io.Serializable;

/**
 *
<code>
BurstList ::= SEQUENCE {
  warningPeriod  [0] INTEGER (1..1200) DEFAULT 30,
  bursts         [1] Burst,
  ...
}
-- warningPeriod is measured in 1 second units.
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface BurstList extends Serializable {

    Integer getWarningPeriod();

    Burst getBursts();

}