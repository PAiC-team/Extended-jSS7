
package org.restcomm.protocols.ss7.cap.api.primitives;

import java.io.Serializable;

/**
 *
<code>
Burst ::= SEQUENCE {
  numberOfBursts   [0] INTEGER (1..3) DEFAULT 1,
  -- 3GPP Release 7 32 3GPP TS 29.078 7.0.0 (2005-06)
  burstInterval    [1] INTEGER (1..1200) DEFAULT 2,
  numberOfTonesInBurst [2] INTEGER (1..3) DEFAULT 3,
  toneDuration     [3] INTEGER (1..20) DEFAULT 2,
  toneInterval     [4] INTEGER (1..20) DEFAULT 2,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface Burst extends Serializable {

    Integer getNumberOfBursts();

    Integer getBurstInterval();

    Integer getNumberOfTonesInBurst();

    Integer getToneDuration();

    Integer getToneInterval();

}