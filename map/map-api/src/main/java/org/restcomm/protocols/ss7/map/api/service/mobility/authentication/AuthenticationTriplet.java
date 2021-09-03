
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

/**
 *
 AuthenticationTriplet ::= SEQUENCE { rand RAND, sres SRES, kc Kc, ...}
 *
 *
 * RAND ::= OCTET STRING (SIZE (16)) SRES ::= OCTET STRING (SIZE (4)) Kc ::= OCTET STRING (SIZE (8))
 *
 * @author sergey vetyutnev
 *
 */
public interface AuthenticationTriplet extends Serializable {

    byte[] getRand();

    byte[] getSres();

    byte[] getKc();

}
