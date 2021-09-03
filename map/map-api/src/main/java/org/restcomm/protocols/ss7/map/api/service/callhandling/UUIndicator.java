
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

/**
*
<code>
UUIndicator ::= OCTET STRING (SIZE (1))
-- Octets are coded according to ETS 300 356

</code>
*
*
* @author sergey vetyutnev
*
*/
public interface UUIndicator extends Serializable {

    int getData();

    // TODO: implement the internal structure

}
