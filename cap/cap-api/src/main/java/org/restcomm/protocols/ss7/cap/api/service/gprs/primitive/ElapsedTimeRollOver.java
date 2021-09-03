
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 ElapsedTimeRollOver ::= CHOICE { rO-TimeGPRSIfNoTariffSwitch [0] INTEGER (0..255), rO-TimeGPRSIfTariffSwitch [1] SEQUENCE {
 * rO-TimeGPRSSinceLastTariffSwitch [0] INTEGER (0..255) OPTIONAL, rO-TimeGPRSTariffSwitchInterval [1] INTEGER (0..255) OPTIONAL
 * } } -- rO-TimeGPRSIfNoTariffSwitch, rO-TimeGPRSSinceLastTariffSwitch and -- rO-TimeGPRSTariffSwitchInterval -- present
 * counters indicating the number of parameter range rollovers.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ElapsedTimeRollOver extends Serializable {

    Integer getROTimeGPRSIfNoTariffSwitch();

    ROTimeGPRSIfTariffSwitch getROTimeGPRSIfTariffSwitch();

}