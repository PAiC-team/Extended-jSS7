
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 timeGPRSIfTariffSwitch [1] SEQUENCE { timeGPRSSinceLastTariffSwitch [0] INTEGER (0..86400), timeGPRSTariffSwitchInterval [1]
 * INTEGER (0..86400) OPTIONAL } }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TimeGPRSIfTariffSwitch extends Serializable {

    int getTimeGPRSSinceLastTariffSwitch();

    Integer getTimeGPRSTariffSwitchInterval();

}