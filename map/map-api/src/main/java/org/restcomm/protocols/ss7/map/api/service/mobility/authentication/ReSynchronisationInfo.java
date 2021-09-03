
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

/**
 *
 * Re-synchronisationInfo ::= SEQUENCE { rand RAND, auts AUTS, ...}
 *
 * RAND ::= OCTET STRING (SIZE (16)) AUTS ::= OCTET STRING (SIZE (14))
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReSynchronisationInfo extends Serializable {

    byte[] getRand();

    byte[] getAuts();

}
