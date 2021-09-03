
package org.restcomm.protocols.ss7.cap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.CauseIndicators;

/**
 *
 ISUP CauseIndicators wrapper
 *
 * Cause {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE( bound.&minCauseLength .. bound.&maxCauseLength)) -- Indicates the
 * cause for interface related information. -- Refer to ETSI EN 300 356-1 [23] Cause parameter for encoding. -- For the use of
 * cause and location values refer to ITU-T Recommendation Q.850 [47] -- Shall always include the cause value and shall also
 * include the diagnostics field, -- if available.
 *
 * minCauseLength ::= 2 maxCauseLength ::= 32
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CauseCap extends Serializable {

    byte[] getData();

    CauseIndicators getCauseIndicators() throws CAPException;

}