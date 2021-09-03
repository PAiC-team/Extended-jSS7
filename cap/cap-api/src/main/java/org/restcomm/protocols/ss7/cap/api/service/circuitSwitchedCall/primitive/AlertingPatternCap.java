
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.primitives.AlertingPattern;

/**
 *
 AlertingPattern ::= OCTET STRING (SIZE(3))
 -- Indicates a specific pattern that is used to alert a subscriber
 -- (e.g. distinctive ringing, tones, etc.).
 -- The encoding of the last octet of this parameter is as defined in 3GPP TS 29.002 [11].
 -- Only the trailing OCTET is used, the remaining OCTETS shall be sent as NULL (zero)
 -- The receiving side shall ignore the leading two OCTETS.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AlertingPatternCap extends Serializable {

    byte[] getData();

    AlertingPattern getAlertingPattern();

}