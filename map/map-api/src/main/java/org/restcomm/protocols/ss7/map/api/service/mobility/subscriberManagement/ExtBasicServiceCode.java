
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

/**
 *
 Ext-BasicServiceCode ::= CHOICE { ext-BearerService [2] Ext-BearerServiceCode, ext-Teleservice [3] Ext-TeleserviceCode}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ExtBasicServiceCode extends Serializable {

    ExtBearerServiceCode getExtBearerService();

    ExtTeleserviceCode getExtTeleservice();

}
