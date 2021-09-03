package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.CallingPartyNumber;

/**
*
<code>
CallingPartyNumber {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE (
bound.&minCallingPartyNumberLength..bound.&maxCallingPartyNumberLength))
-- Indicates the Calling Party Number. Refer to ITU-T Recommendation Q.763 for encoding.

minCallingPartyNumberLength=1
maxCallingPartyNumberLength=5
</code>

*
* @author sergey vetyutnev
*
*/
public interface CallingPartyNumberInap extends Serializable {

    byte[] getData();

    CallingPartyNumber getCallingPartyNumber() throws INAPException;

}
