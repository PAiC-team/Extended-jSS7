
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 lassmarkInfo ::= octet STRING (SIZE (1..2)) -- classmark information element type 1 or 2 contents as defined -- in TS GSM
 * 08.08 (phase 1)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ClassmarkInfo extends Serializable {

    byte[] getData();

}
