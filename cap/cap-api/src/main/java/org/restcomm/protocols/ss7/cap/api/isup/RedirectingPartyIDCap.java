
package org.restcomm.protocols.ss7.cap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.RedirectingNumber;

/**
 *
 ISUP RedirectingNumber wrapper
 *
 * RedirectingPartyID {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE ( bound.&minRedirectingPartyIDLength ..
 * bound.&maxRedirectingPartyIDLength)) -- Indicates redirecting number. -- Refer to ETSI EN 300 356-1 [23] Redirecting number
 * for encoding.
 *
 * minRedirectingPartyIDLength ::= 2 maxRedirectingPartyIDLength ::= 10
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RedirectingPartyIDCap extends Serializable {

    byte[] getData();

    RedirectingNumber getRedirectingNumber() throws CAPException;

}