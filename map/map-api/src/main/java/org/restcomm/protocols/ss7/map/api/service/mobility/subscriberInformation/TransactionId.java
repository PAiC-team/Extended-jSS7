
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

/**
 *
<code>
TransactionId ::= OCTET STRING (SIZE (1..2))
-- This type carries the value part of the transaction identifier which is used in the
-- session management messages on the access interface. The encoding is defined in
-- 3GPP TS 24.008
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TransactionId extends Serializable {

    byte[] getData();

}
