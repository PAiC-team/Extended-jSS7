
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.CAPExtensions;

/**
 *
<code>
AChBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE (bound.&minAChBillingChargingLength .. bound.&maxAChBillingChargingLength))
(CONSTRAINED BY {
-- shall be the result of the BER-encoded value of the type
-- CAMEL-AChBillingChargingCharacteristics {bound}})
-- The AChBillingChargingCharacteristics parameter specifies the charging related information
-- to be provided by the gsmSSF and the conditions on which this information has to be reported
-- back to the gsmSCF with the ApplyChargingReport operation. The value of the
-- AChBillingChargingCharacteristics of type OCTET STRING carries a value of the ASN.1 data type:
-- CAMEL-AChBillingChargingCharacteristics. The normal encoding rules are used to encode this
-- value.
-- The violation of the UserDefinedConstraint shall be handled as an ASN.1 syntax error.

CAMEL V2:
CAMEL-AChBillingChargingCharacteristics ::= CHOICE {
  timeDurationCharging [0] SEQUENCE {
    maxCallPeriodDuration      [0] INTEGER (1..864000),
    releaseIfdurationExceeded  [1] ReleaseIfDurationExceeded OPTIONAL,
    tariffSwitchInterval       [2] INTEGER (1..86400) OPTIONAL
  }
}
ReleaseIfDurationExceeded ::= SEQUENCE {
  tone BOOLEAN DEFAULT FALSE,
  ...,
  extensions [10] SEQUENCE SIZE(1..numOfExtensions) OF ExtensionField OPTIONAL
}

CAMEL V3:
CAMEL-AChBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= CHOICE {
  timeDurationCharging [0] SEQUENCE {
    maxCallPeriodDuration      [0] INTEGER (1..864000),
    releaseIfdurationExceeded  [1] BOOLEAN DEFAULT FALSE,
    tariffSwitchInterval       [2] INTEGER (1..86400) OPTIONAL,
    tone                       [3] BOOLEAN DEFAULT FALSE,
    extensions                 [4] Extensions {bound} OPTIONAL,
    ...
  }
}

CAMEL V4:
CAMEL-AChBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= CHOICE {
  timeDurationCharging [0] SEQUENCE {
    maxCallPeriodDuration      [0] INTEGER (1..864000),
    releaseIfdurationExceeded  [1] BOOLEAN DEFAULT FALSE,
    tariffSwitchInterval       [2] INTEGER (1..86400) OPTIONAL,
    audibleIndicator           [3] AudibleIndicator DEFAULT tone: FALSE,
    extensions                 [4] Extensions {bound} OPTIONAL,
    ...
  }
}
-- tariffSwitchInterval is measured in 1 second units.
-- maxCallPeriodDuration is measured in 100 millisecond units
</code>
 *
 *
 * @author sergey vetyutnev
 * @author alerant appngin
 *
 */
public interface CAMELAChBillingChargingCharacteristics extends Serializable {

    byte[] getData();

    long getMaxCallPeriodDuration();

    boolean getReleaseIfDurationExceeded();

    Long getTariffSwitchInterval();

    AudibleIndicator getAudibleIndicator();

    CAPExtensions getExtensions();

}
