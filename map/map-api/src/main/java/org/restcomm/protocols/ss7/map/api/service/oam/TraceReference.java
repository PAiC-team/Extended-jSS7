
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
TraceReference ::= OCTET STRING (SIZE (1..2))
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface TraceReference extends Serializable {

    byte[] getData();

}
