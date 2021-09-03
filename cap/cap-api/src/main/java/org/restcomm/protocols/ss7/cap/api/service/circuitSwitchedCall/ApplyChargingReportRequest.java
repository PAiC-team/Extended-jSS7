
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.TimeDurationChargingResult;

/**
 * <p>
 * This is the report that is sent from gsmSSF to gsmSCF at the end of a call period or when the call is released. In addition,
 * when call set up failure occurs, such as called party busy or no answer, the gsmSSF also sends a charging report (if
 * previously requested).
 * </p>
 *
<code>
applyChargingReport {PARAMETERS-BOUND : bound} OPERATION ::= {
  ARGUMENT ApplyChargingReportArg {bound}
  RETURN RESULT FALSE
  ERRORS {missingParameter | unexpectedComponentSequence | unexpectedParameter | unexpectedDataValue | unknownCSID |
          unknownLegID | parameterOutOfRange | systemFailure | taskRefused}
  CODE opcode-applyChargingReport
}
-- Direction: gsmSSF -> gsmSCF, Timer: Tacr
-- This operation is used by the gsmSSF to report to the gsmSCF the occurrence of a
-- specific charging event as requested by the gsmSCF using the ApplyCharging operation.

ApplyChargingReportArg {PARAMETERS-BOUND : bound} ::= CallResult {bound}

CallResult {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE( bound.&minCallResultLength .. bound.&maxCallResultLength))
  (CONSTRAINED BY {
  -- shall be the result of the BER-encoded value of type - CAMEL-CallResult {bound}
})
-- The violation of the UserDefinedConstraint shall be handled as an ASN.1 syntax error.
-- This parameter provides the gsmSCF with the charging related information previously requested
-- using the ApplyCharging operation. This shall include the partyToCharge parameter as
-- received in the related ApplyCharging operation to correlate the result to the request

CAMEL-CallResult {PARAMETERS-BOUND : bound} ::= CHOICE {
  timeDurationChargingResult [0] SEQUENCE {
    partyToCharge     [0] ReceivingSideID,
    timeInformation   [1] TimeInformation,
    legActive         [2] BOOLEAN DEFAULT TRUE,
    callLegReleasedAtTcpExpiry [3] NULL OPTIONAL,
    extensions        [4] Extensions {bound} OPTIONAL,
    aChChargingAddress [5] AChChargingAddress {bound} DEFAULT legID:receivingSideID:leg1,
    ...
  }
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ApplyChargingReportRequest extends CircuitSwitchedCallMessage {

    TimeDurationChargingResult getTimeDurationChargingResult();

}
