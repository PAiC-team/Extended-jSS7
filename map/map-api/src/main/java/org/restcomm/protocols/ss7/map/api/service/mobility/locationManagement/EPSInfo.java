
package org.restcomm.protocols.ss7.map.api.service.mobility.locationManagement;

import java.io.Serializable;

/**
 *
 EPS-Info ::= CHOICE{ pdn-gw-update [0] PDN-GW-Update, isr-Information [1] ISR-Information }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EPSInfo extends Serializable {

    PDNGWUpdate getPndGwUpdate();

    ISRInformation getIsrInformation();

}
