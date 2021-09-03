
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 volumeIfTariffSwitch [1] SEQUENCE { volumeSinceLastTariffSwitch [0] INTEGER (0..4294967295), volumeTariffSwitchInterval [1]
 * INTEGER (0..4294967295) OPTIONAL }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface VolumeIfTariffSwitch extends Serializable {

    long getVolumeSinceLastTariffSwitch();

    Long getVolumeTariffSwitchInterval();

}