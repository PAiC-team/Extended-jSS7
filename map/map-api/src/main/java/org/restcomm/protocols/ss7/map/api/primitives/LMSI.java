package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 * LMSI ::= OCTET STRING (SIZE (4))
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LMSI extends Serializable {

    byte[] getData();

}
