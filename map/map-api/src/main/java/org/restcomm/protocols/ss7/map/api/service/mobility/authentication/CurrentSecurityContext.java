
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

/**
 *
 CurrentSecurityContext ::= CHOICE { gsm-SecurityContextData [0] GSM-SecurityContextData, umts-SecurityContextData [1]
 * UMTS-SecurityContextData }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CurrentSecurityContext extends Serializable {

    GSMSecurityContextData getGSMSecurityContextData();

    UMTSSecurityContextData getUMTSSecurityContextData();

}
