
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 RadioResourceInformation ::= OCTET STRING (SIZE (3..13)) -- Octets are coded according the Channel Type information element
 * in 3GPP TS 48.008
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RadioResourceInformation extends Serializable {

    byte[] getData();

}
