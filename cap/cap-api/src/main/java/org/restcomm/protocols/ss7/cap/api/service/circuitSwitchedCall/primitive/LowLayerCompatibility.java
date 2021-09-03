
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
LowLayerCompatibility {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE ( bound.&minLowLayerCompatibilityLength .. bound.&maxLowLayerCompatibilityLength))
-- indicates the LowLayerCompatibility for the calling party.
-- Refer to 3GPP TS 24.008 [9] for encoding.
-- It shall be coded as in the value part defined in 3GPP TS 24.008.
-- i.e. the 3GPP TS 24.008 IEI and 3GPP TS 24.008 octet length indicator
-- shall not be included.

minLowLayerCompatibilityLength ::= 1
maxLowLayerCompatibilityLength ::= 16

The purpose of the low layer compatibility information element is to provide a means which should be used for
compatibility checking by an addressed entity (e.g., a remote user or an interworking unit or a high layer function
network node addressed by the calling user). The low layer compatibility information element is transferred
transparently by a PLMN between the call originating entity (e.g. the calling user) and the addressed entity.
Except for the information element identifier, the low layer compatibility information element is coded as in ITU
recommendation Q.931.
For backward compatibility reasons coding of the modem type field according to ETS 300 102-1 (12-90) shall also be
supported.
The low layer compatibility is a type 4 information element with a minimum length of 2 octets and a maximum length
of 18 octets.

The following octets are coded
as described in
ITU Recommendation Q.931
(Coding of the modem type according to both Q.931 and
ETS 300 102-1 (12-90) shall be accepted)
</code>
 *
 * }
 *
 * @author sergey vetyutnev
 *
 */
public interface LowLayerCompatibility extends Serializable {

    byte[] getData();

    // TODO: implement the internal structure according to Q.931
    // I do not know where to put messages from Q.931

}
