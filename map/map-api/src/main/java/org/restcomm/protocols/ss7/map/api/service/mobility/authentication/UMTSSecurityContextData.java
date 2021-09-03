
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

/**
 *
 UMTS-SecurityContextData ::= SEQUENCE { ck CK, ik IK, ksi KSI, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface UMTSSecurityContextData extends Serializable {

    CK getCK();

    IK getIK();

    KSI getKSI();

}
