
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

/**
*
<code>
UUI  ::= OCTET STRING (SIZE (1..131))
-- Octets are coded according to ETS 300 356

</code>
*
*
* @author sergey vetyutnev
*
*/
public interface UUI extends Serializable {

    byte[] getData();

    // TODO: implement the internal structure

}
