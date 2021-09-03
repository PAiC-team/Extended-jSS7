
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
<code>
BasicServiceCode ::= CHOICE {
  bearerService [2] BearerServiceCode,
  teleservice   [3] TeleserviceCode
}
</code>
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface BasicServiceCode extends Serializable {

    BearerServiceCode getBearerService();

    TeleserviceCode getTeleservice();

}
