
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
 SCIBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE ( bound.&minSCIBillingChargingLength ..
 * bound.&maxSCIBillingChargingLength)) (CONSTRAINED BY {-- shall be the result of the BER-encoded value of type
 * CAMEL-SCIBillingChargingCharacteristics}) -- Indicates AOC information to be sent to a Mobile Station -- The violation of the
 * UserDefinedConstraint shall be handled as an ASN.1 syntax error.
 *
 * minSCIBillingChargingLength ::= 4 maxSCIBillingChargingLength ::= 255
 *
 * CAMEL-SCIBillingChargingCharacteristics ::= CHOICE { aOCBeforeAnswer [0] AOCBeforeAnswer, aOCAfterAnswer [1] AOCSubsequent,
 * aOC-extension [2] CAMEL-SCIBillingChargingCharacteristicsAlt }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SCIBillingChargingCharacteristics extends Serializable {

    AOCBeforeAnswer getAOCBeforeAnswer();

    AOCSubsequent getAOCSubsequent();

    CAMELSCIBillingChargingCharacteristicsAlt getAOCExtension();

}