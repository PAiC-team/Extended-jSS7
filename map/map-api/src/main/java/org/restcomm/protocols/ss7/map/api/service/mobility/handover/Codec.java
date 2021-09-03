
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 Codec ::= OCTET STRING (SIZE (1..4))
 *
 * -- The internal structure is defined as follows: -- octet 1 Coded as Codec Identification code in 3GPP TS 26.103 -- octets
 * 2,3,4 Parameters for the Codec as defined in 3GPP TS -- 26.103, if available, length depending on the codec
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface Codec extends Serializable {

    byte[] getData();

}
