
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
CUG-Index ::= INTEGER (0..32767)
-- The internal structure is defined in ETS 300 138.
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface CUGIndex extends Serializable {

    int getData();

}
