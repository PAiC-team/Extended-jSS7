
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 TransferredVolumeRollOver ::= CHOICE { rO-VolumeIfNoTariffSwitch [0] INTEGER (0..255), rO-VolumeIfTariffSwitch [1] SEQUENCE {
 * rO-VolumeSinceLastTariffSwitch [0] INTEGER (0..255) OPTIONAL, rO-VolumeTariffSwitchInterval [1] INTEGER (0..255) OPTIONAL } }
 * -- rO-VolumeIfNoTariffSwitch, rO-VolumeSinceLastTariffSwitch and rO-VolumeTariffSwitchInterval -- present counters indicating
 * the number of parameter range rollovers.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TransferredVolumeRollOver extends Serializable {

    Integer getROVolumeIfNoTariffSwitch();

    ROVolumeIfTariffSwitch getROVolumeIfTariffSwitch();

}