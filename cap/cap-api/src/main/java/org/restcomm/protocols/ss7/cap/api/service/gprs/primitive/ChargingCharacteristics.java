
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 ChargingCharacteristics ::= CHOICE { maxTransferredVolume [0] INTEGER (1..4294967295), maxElapsedTime [1] INTEGER (1..86400)
 * } -- maxTransferredVolume is measured in number of bytes -- maxElapsedTime is measured in seconds
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ChargingCharacteristics extends Serializable {

    long getMaxTransferredVolume();

    int getMaxElapsedTime();

}