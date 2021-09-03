
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 TransferredVolume ::= CHOICE { volumeIfNoTariffSwitch [0] INTEGER (0..4294967295), volumeIfTariffSwitch [1] SEQUENCE {
 * volumeSinceLastTariffSwitch [0] INTEGER (0..4294967295), volumeTariffSwitchInterval [1] INTEGER (0..4294967295) OPTIONAL } }
 * -- volumeIfNoTariffSwitch, volumeSinceLastTariffSwitch and volumeTariffSwitchInterval -- are measured in bytes.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TransferredVolume extends Serializable {

    Long getVolumeIfNoTariffSwitch();

    VolumeIfTariffSwitch getVolumeIfTariffSwitch();

}