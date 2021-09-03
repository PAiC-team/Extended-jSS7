
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

/**
 *
 IK ::= OCTET STRING (SIZE (16))
 *
 *
 * @author Lasith Waruna Perera
 *
 */
public interface IK extends Serializable {

    byte[] getData();

}
