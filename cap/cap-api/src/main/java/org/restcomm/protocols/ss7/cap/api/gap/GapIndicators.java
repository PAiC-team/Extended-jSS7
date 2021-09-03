
package org.restcomm.protocols.ss7.cap.api.gap;

import java.io.Serializable;

/**
 *
<code>
GapIndicators ::= SEQUENCE {
  duration    [0] Duration,
  gapInterval [1] Interval,
  ...
}
-- Indicates the call gapping characteristics.
-- No call gapping when gapInterval equals 0

Duration ::= INTEGER (-2..86400)
Interval ::= INTEGER (-1..60000)
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GapIndicators extends Serializable {

    int getDuration();

    int getGapInterval();

}