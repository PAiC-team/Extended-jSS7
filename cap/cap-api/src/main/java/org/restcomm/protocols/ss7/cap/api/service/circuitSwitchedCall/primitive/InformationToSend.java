
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
InformationToSend {PARAMETERS-BOUND : bound} ::= CHOICE {
  inbandInfo  [0] InbandInfo {bound},
  tone        [1] Tone
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface InformationToSend extends Serializable {

    InbandInfo getInbandInfo();

    Tone getTone();

}
