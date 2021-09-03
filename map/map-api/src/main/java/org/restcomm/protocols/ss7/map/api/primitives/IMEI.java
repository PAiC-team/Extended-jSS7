
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 *
<code>
IMEI ::= TBCD-STRING (SIZE (8))
-- Refers to International Mobile Station Equipment Identity
-- and Software Version Number (SVN) defined in TS 3GPP TS 23.003 [17].
-- If the SVN is not present the last octet shall contain the
-- digit 0 and a filler.
-- If present the SVN shall be included in the last octet.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface IMEI extends Serializable {

    String getIMEI();

}
