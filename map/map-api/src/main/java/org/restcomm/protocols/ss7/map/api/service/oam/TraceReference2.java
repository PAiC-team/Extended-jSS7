
package org.restcomm.protocols.ss7.map.api.service.oam;

import java.io.Serializable;

/**
 *
<code>
TraceReference2 ::= OCTET STRING (SIZE (3))
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TraceReference2 extends Serializable {

    byte[] getData();

}
