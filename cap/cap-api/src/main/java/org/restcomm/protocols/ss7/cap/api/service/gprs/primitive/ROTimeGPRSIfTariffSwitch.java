
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 rO-TimeGPRSIfTariffSwitch [1] SEQUENCE { rO-TimeGPRSSinceLastTariffSwitch [0] INTEGER (0..255) OPTIONAL,
 * rO-TimeGPRSTariffSwitchInterval [1] INTEGER (0..255) OPTIONAL }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ROTimeGPRSIfTariffSwitch extends Serializable {

    Integer getROTimeGPRSSinceLastTariffSwitch();

    Integer getROTimeGPRSTariffSwitchInterval();

}