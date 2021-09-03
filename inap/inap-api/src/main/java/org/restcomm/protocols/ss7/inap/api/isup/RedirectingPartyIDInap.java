package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.RedirectingNumber;

/**
*

<code>
RedirectingPartyID {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE (
bound.&minRedirectingPartyIDLength.. bound.&maxRedirectingPartyIDLength))
-- Indicates redirecting number.
-- Refer to the ITU-T Recommendation Q.763 Redirecting number for encoding.
</code>

*
* @author sergey vetyutnev
*
*/
public interface RedirectingPartyIDInap extends Serializable {

    byte[] getData();

    RedirectingNumber getRedirectingNumber() throws INAPException;

}
