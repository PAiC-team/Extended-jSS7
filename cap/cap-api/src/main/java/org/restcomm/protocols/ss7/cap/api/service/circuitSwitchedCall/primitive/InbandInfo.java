
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
InbandInfo {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  messageID            [0] MessageID {bound},
  numberOfRepetitions  [1] INTEGER (1..127) OPTIONAL,
  duration             [2] INTEGER (0..32767) OPTIONAL,
  interval             [3] INTEGER (0..32767) OPTIONAL,
  ...
}
-- Interval is the time in seconds between each repeated announcement. Duration is the total
-- amount of time in seconds, including repetitions and intervals.
-- The end of announcement is either the end of duration or numberOfRepetitions,
-- whatever comes first.
-- duration with value 0 indicates infinite duration
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface InbandInfo extends Serializable {

    MessageID getMessageID();

    Integer getNumberOfRepetitions();

    Integer getDuration();

    Integer getInterval();

}
