
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

/**
 *
 * SuperChargerInfo ::= CHOICE { sendSubscriberData [0] NULL, subscriberDataStored [1] AgeIndicator }
 *
 * AgeIndicator ::= OCTET STRING (SIZE (1..6)) -- The internal structure of this parameter is implementation specific.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SuperChargerInfo extends Serializable {

    Boolean getSendSubscriberData();

    byte[] getSubscriberDataStored();

}
