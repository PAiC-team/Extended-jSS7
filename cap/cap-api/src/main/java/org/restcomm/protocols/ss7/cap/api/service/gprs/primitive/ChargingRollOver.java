
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 ChargingRollOver ::= CHOICE { transferredVolumeRollOver [0] TransferredVolumeRollOver, elapsedTimeRollOver [1]
 * ElapsedTimeRollOver } -- transferredVolumeRollOver shall be reported if ApplyChargingReportGPRS reports volume and -- a
 * roll-over has occurred in one or more volume counters. Otherwise, it shall be absent. -- elapsedTimeRollOver shall be
 * reported if ApplyChargingReportGPRS reports duration and -- a roll-over has occurred in one or more duration counters.
 * Otherwise, it shall be absent.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ChargingRollOver extends Serializable {

    TransferredVolumeRollOver getTransferredVolumeRollOver();

    ElapsedTimeRollOver getElapsedTimeRollOver();

}