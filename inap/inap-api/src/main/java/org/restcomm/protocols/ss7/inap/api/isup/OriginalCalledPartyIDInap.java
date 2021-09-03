package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.OriginalCalledNumber;

/**
*
<code>
OriginalCalledPartyID {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE(
bound.&minOriginalCalledPartyIDLength..bound.&maxOriginalCalledPartyIDLength))
-- Indicates the original called number.
-- Refer to the ITU-T Recommendation Q.763 Original Called Number for encoding.

minOriginalCalledPartyIDLength=1
maxOriginalCalledPartyIDLength=5
</code>
*
* @author sergey vetyutnev
*
*/
public interface OriginalCalledPartyIDInap extends Serializable {

    byte[] getData();

    OriginalCalledNumber getOriginalCalledNumber() throws INAPException;

}
