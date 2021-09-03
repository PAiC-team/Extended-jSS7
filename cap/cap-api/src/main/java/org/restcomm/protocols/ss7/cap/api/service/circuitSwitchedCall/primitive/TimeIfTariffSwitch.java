
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
 TimeIfTariffSwitch ::= SEQUENCE { timeSinceTariffSwitch [0] INTEGER(0..864000), tariffSwitchInterval [1] INTEGER(1..864000)
 * OPTIONAL } -- timeSinceTariffSwitch and tariffSwitchInterval are measured in 100 millisecond intervals
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TimeIfTariffSwitch extends Serializable {

    int getTimeSinceTariffSwitch();

    Integer getTariffSwitchInterval();

}