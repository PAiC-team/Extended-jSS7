
package org.restcomm.protocols.ss7.map.api.primitives;

import java.io.Serializable;

/**
 *
 TMSI ::= OCTET STRING (SIZE (1..4))
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TMSI extends Serializable {

    byte[] getData();

}
