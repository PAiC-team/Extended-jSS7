
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 AoIPCodec ::= OCTET STRING (SIZE (1..3))
 *
 * -- The internal structure is defined as follows: -- octet 1 Coded as Speech Codec Elements in 3GPP TS 48.008 -- with the
 * exception that FI, PI, PT and TF bits shall -- be set to 0 -- octets 2,3 Optional; in case of AMR codec types it defines --
 * the supported codec configurations as defined in -- 3GPP TS 48.008
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AoIPCodec extends Serializable {

    byte[] getData();

}
