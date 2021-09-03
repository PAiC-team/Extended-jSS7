
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.primitives.AChChargingAddress;
import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;
import org.restcomm.protocols.ss7.cap.api.primitives.SendingSideID;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CAMELAChBillingChargingCharacteristics;

/**
 * <p>
 * This is the instruction from the gsmSCF to the gsmSSF to start or continue monitoring the call duration
 * </p>
 * <p>
 * See also {@link ApplyChargingReportRequest}
 * </p>
 *
<code>
applyCharging {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT ApplyChargingArg {bound}
  RETURN RESULT FALSE
  ERRORS {missingParameter | unexpectedComponentSequence | unexpectedParameter | unexpectedDataValue | parameterOutOfRange |
          systemFailure | taskRefused | unknownLegID | unknownCSID}
  CODE opcode-applyCharging
}
-- Direction: gsmSCF -> gsmSSF, Timer: Tac
-- This operation is used for interacting from the gsmSCF with the gsmSSF charging mechanisms.
-- The ApplyChargingReport operation provides the feedback from the gsmSSF to the gsmSCF.

ApplyChargingArg {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  aChBillingChargingCharacteristics   [0] AChBillingChargingCharacteristics {bound},
  partyToCharge                       [2] SendingSideID DEFAULT sendingSideID : leg1,
  extensions                          [3] Extensions {bound} OPTIONAL,
  aChChargingAddress                  [50] AChChargingAddress {bound} DEFAULT legID:sendingSideID:leg1,
  ...
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ApplyChargingRequest extends CircuitSwitchedCallMessage {

    CAMELAChBillingChargingCharacteristics getAChBillingChargingCharacteristics();

    SendingSideID getPartyToCharge();

    CAPExtensions getExtensions();

    AChChargingAddress getAChChargingAddress();

}
