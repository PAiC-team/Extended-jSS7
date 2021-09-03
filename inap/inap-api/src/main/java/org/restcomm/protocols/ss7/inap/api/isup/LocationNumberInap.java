package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.LocationNumber;

/**
*
<code>
LocationNumber {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE (
bound.&minLocationNumberLength..bound.&maxLocationNumberLength))
-- Indicates the Location Number for the calling party.
-- Refer to ITU-T Recommendation Q.763 (White book) for encoding.

minLocationNumberLength=1
maxLocationNumberLength=5
</code>
*
* @author sergey vetyutnev
*
*/
public interface LocationNumberInap extends Serializable {

    byte[] getData();

    LocationNumber getLocationNumber() throws INAPException;

}
