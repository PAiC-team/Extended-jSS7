
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
<code>
variableMessage [30] SEQUENCE {
  elementaryMessageID [0] Integer4,
  variableParts       [1] SEQUENCE SIZE (1..5) OF VariablePart {bound}
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface VariableMessage extends Serializable {

    int getElementaryMessageID();

    ArrayList<VariablePart> getVariableParts();

}
