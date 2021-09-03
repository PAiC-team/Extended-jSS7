
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
 TeleserviceCode ::= OCTET STRING (SIZE (1))
 -- This type is used to represent the code identifying a single
 -- teleservice, a group of teleservices, or all teleservices. The
 -- services are defined in TS GSM 22.003 [4].
 -- The internal structure is defined as follows:

-- bits 87654321: group (bits 8765) and specific service
-- (bits 4321)
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TeleserviceCode extends Serializable {

    int getData();

    TeleserviceCodeValue getTeleserviceCodeValue();

}
