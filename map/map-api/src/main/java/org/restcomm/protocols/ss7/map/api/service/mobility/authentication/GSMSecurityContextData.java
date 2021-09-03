
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

/**
 *
 GSM-SecurityContextData ::= SEQUENCE { kc Kc, cksn Cksn, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GSMSecurityContextData extends Serializable {

    Kc getKc();

    Cksn getCksn();

}
