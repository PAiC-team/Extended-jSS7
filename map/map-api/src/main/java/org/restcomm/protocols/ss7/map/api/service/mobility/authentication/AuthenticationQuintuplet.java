
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

/**
 *
 AuthenticationQuintuplet ::= SEQUENCE { rand RAND, xres XRES, ck CK, ik IK, autn AUTN, ...}
 *
 *
 * RAND ::= OCTET STRING (SIZE (16)) XRES ::= OCTET STRING (SIZE (4..16)) CK ::= OCTET STRING (SIZE (16)) IK ::= OCTET STRING
 * (SIZE (16)) AUTN ::= OCTET STRING (SIZE (16))
 *
 * @author sergey vetyutnev
 *
 */
public interface AuthenticationQuintuplet extends Serializable {

    byte[] getRand();

    byte[] getXres();

    byte[] getCk();

    byte[] getIk();

    byte[] getAutn();

}
