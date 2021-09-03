
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 ElapsedTime ::= CHOICE { timeGPRSIfNoTariffSwitch [0] INTEGER (0..86400), timeGPRSIfTariffSwitch [1] SEQUENCE {
 * timeGPRSSinceLastTariffSwitch [0] INTEGER (0..86400), timeGPRSTariffSwitchInterval [1] INTEGER (0..86400) OPTIONAL } } --
 * timeGPRSIfNoTariffSwitch is measured in seconds -- timeGPRSSinceLastTariffSwitch and timeGPRSTariffSwitchInterval are
 * measured in seconds
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ElapsedTime extends Serializable {

    Integer getTimeGPRSIfNoTariffSwitch();

    TimeGPRSIfTariffSwitch getTimeGPRSIfTariffSwitch();

}