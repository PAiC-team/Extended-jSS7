
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation;

import java.io.Serializable;

import org.restcomm.protocols.ss7.isup.message.parameter.LocationNumber;
import org.restcomm.protocols.ss7.map.api.MAPException;

/**
 *
<code>
LocationNumber ::= OCTET STRING (SIZE (2..10))
-- the internal structure is defined in ITU-T Rec Q.763
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LocationNumberMap extends Serializable {

    byte[] getData();

    LocationNumber getLocationNumber() throws MAPException;

}
