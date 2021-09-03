
package org.restcomm.protocols.ss7.cap.api.gap;

import java.io.Serializable;

/**
 *
<code>
GapOnService ::= SEQUENCE {
  serviceKey [0] ServiceKey,
  ...
}

ServiceKey::= Integer4
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface GapOnService extends Serializable {

    int getServiceKey();

}