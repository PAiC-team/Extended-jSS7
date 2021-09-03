
package org.restcomm.protocols.ss7.map.api.service.lsm;

import java.io.Serializable;

/**
 *
 GeranGANSSpositioningData ::= OCTET STRING (SIZE (2..10)) -- Refers to the GANSS Positioning Data defined in 3GPP TS 49.031.
 * -- This is composed of 2 or more octets with an internal structure according to -- 3GPP TS 49.031.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GeranGANSSpositioningData extends Serializable {

    byte[] getData();

}
