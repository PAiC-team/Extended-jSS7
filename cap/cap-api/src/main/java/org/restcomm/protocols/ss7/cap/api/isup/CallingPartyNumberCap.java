
package org.restcomm.protocols.ss7.cap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.CallingPartyNumber;

/**
 *
 *
<code>
ISUP CallingPartyNumber wrapper

CallingPartyNumber {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE( bound.&minCallingPartyNumberLength .. bound.&maxCallingPartyNumberLength))
-- Indicates the Calling Party Number. Refer to ETSI EN 300 356-1 [23] for encoding.

minCallingPartyNumberLength ::= 2
maxCallingPartyNumberLength ::= 10
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallingPartyNumberCap extends Serializable {

    byte[] getData();

    CallingPartyNumber getCallingPartyNumber() throws CAPException;

}