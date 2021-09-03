
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
Tone ::= SEQUENCE {
  toneID   [0] Integer4,
  duration [1] Integer4 OPTIONAL,
  ...
}
-- The duration specifies the length of the tone in seconds, value 0 indicates infinite duration.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface Tone extends Serializable {

    int getToneID();

    Integer getDuration();

}
