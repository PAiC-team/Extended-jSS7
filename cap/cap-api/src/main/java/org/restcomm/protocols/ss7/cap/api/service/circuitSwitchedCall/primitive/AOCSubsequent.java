
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
 AOCSubsequent ::= SEQUENCE { cAI-GSM0224 [0] CAI-GSM0224 , tariffSwitchInterval [1] INTEGER (1..86400) OPTIONAL } --
 * tariffSwitchInterval is measured in 1 second units
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AOCSubsequent extends Serializable {

    CAI_GSM0224 getCAI_GSM0224();

    Integer getTariffSwitchInterval();

}