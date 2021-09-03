
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

/**
 *
 CK ::= OCTET STRING (SIZE (16))
 *
 *
 * @author Lasith Waruna Perera
 *
 */
public interface CK extends Serializable {

    byte[] getData();

}
