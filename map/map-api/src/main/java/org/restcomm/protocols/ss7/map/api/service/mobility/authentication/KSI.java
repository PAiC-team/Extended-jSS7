
package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;

/**
 *
 KSI ::= OCTET STRING (SIZE (1)) -- The internal structure is defined in 3GPP TS 24.008
 *
 *
 * @author Lasith Waruna Perera
 *
 */
public interface KSI extends Serializable {

    int getData();

}
