
package org.restcomm.protocols.ss7.cap.api.primitives;

import java.io.Serializable;

/**
 *
<code>
ScfID {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE( bound.&minScfIDLength .. bound.&maxScfIDLength))
-- defined by network operator. -- Indicates the gsmSCF identity.

minScfIDLength ::= 2
maxScfIDLength ::= 10
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ScfID extends Serializable {

    byte[] getData();

}