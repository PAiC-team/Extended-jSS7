
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.isup.Digits;

/**
 *
<code>
ReceivedInformationArg {PARAMETERS-BOUND : bound}::= CHOICE {
  digitsResponse [0] Digits {bound}
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface PromptAndCollectUserInformationResponse extends CircuitSwitchedCallMessage {

    Digits getDigitsResponse();

}
