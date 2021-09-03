
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
Ext-TeleserviceCode ::= OCTET STRING (SIZE (1..5))
-- This type is used to represent the code identifying a single
-- teleservice, a group of teleservices, or all teleservices. The
-- services are defined in TS GSM 22.003 [4].
-- The internal structure is defined as follows:

-- OCTET 1:
-- bits 87654321: group (bits 8765) and specific service
-- (bits 4321)
-- OCTETS 2-5: reserved for future use. If received the
-- Ext-TeleserviceCode shall be
-- treated according to the exception handling defined for the
-- operation that uses this type.

-- Ext-TeleserviceCode includes all values defined for TeleserviceCode.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtTeleserviceCode extends Serializable {

    byte[] getData();

    TeleserviceCodeValue getTeleserviceCodeValue();

}
