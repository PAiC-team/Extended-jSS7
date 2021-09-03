package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.CalledPartyNumber;

/**
*
<code>
CalledPartyNumber {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE
(bound.&minCalledPartyNumberLength .. bound.&maxCalledPartyNumberLength))
-- Indicates the Called Party Number. Refer to ITU-T Recommendation Q.763 for encoding.
</code>

*
* @author sergey vetyutnev
*
*/
public interface CalledPartyNumberInap extends Serializable {

    byte[] getData();

    CalledPartyNumber getCalledPartyNumber() throws INAPException;

}
