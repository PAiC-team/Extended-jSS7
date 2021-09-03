
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
 TimeInformation ::= CHOICE { timeIfNoTariffSwitch [0] TimeIfNoTariffSwitch, timeIfTariffSwitch [1] TimeIfTariffSwitch } --
 * Indicates call duration information
 *
 * TimeIfNoTariffSwitch ::= INTEGER(0..864000) -- TimeIfNoTariffSwitch is measured in 100 millisecond intervals
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TimeInformation extends Serializable {

    Integer getTimeIfNoTariffSwitch();

    TimeIfTariffSwitch getTimeIfTariffSwitch();

}