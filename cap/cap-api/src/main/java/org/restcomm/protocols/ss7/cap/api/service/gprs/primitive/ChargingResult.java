
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 ChargingResult ::= CHOICE { transferredVolume [0] TransferredVolume, elapsedTime [1] ElapsedTime }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ChargingResult extends Serializable {

    TransferredVolume getTransferredVolume();

    ElapsedTime getElapsedTime();

}