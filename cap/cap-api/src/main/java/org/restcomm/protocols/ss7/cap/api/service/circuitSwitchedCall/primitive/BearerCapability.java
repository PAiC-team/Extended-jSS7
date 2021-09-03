
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.BearerCap;

/**
 *
BearerCapability {PARAMETERS-BOUND : bound} ::= CHOICE {
     bearerCap [0] OCTET STRING (SIZE(2..bound.&maxBearerCapabilityLength))
}
-- Indicates the type of bearer capability connection to the user. For bearerCap, the ISUP User
-- Service Information, ETSI EN 300 356-1 [23] -- encoding shall be used.
 *
 * MAXIMUM-FOR-BEARER-CAPABILITY ::= 11
 *
 * @author sergey vetyutnev
 *
 */
public interface BearerCapability extends Serializable {

    BearerCap getBearerCap();

}