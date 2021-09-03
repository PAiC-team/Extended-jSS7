
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
ReportingTrigger ::= OCTET STRING (SIZE (1))
-- Octet is coded as described in 3GPP TS 32.422.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReportingTrigger extends Serializable {

    int getData();

}
