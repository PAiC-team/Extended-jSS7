
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 rO-VolumeIfTariffSwitch [1] SEQUENCE { rO-VolumeSinceLastTariffSwitch [0] INTEGER (0..255) OPTIONAL,
 * rO-VolumeTariffSwitchInterval [1] INTEGER (0..255) OPTIONAL }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ROVolumeIfTariffSwitch extends Serializable {

    Integer getROVolumeSinceLastTariffSwitch();

    Integer getROVolumeTariffSwitchInterval();

}