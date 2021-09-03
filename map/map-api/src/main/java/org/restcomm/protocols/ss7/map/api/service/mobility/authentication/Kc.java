
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

/**
 *
 Kc ::= OCTET STRING (SIZE (8))
 *
 *
 * @author Lasith Waruna Perera
 *
 */
public interface Kc extends Serializable {

    byte[] getData();

}
