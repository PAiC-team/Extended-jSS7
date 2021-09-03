
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
Category ::= OCTET STRING (SIZE (1))
-- The internal structure is defined in ITU-T Rec Q.763.
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface Category extends Serializable {

    int getData();

    CategoryValue getCategoryValue();

}
