
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
<code>
MessageID {PARAMETERS-BOUND : bound} ::= CHOICE {
  elementaryMessageID   [0] Integer4,
  text                  [1] SEQUENCE {
    messageContent [0] IA5String (SIZE(bound.&minMessageContentLength .. bound.&maxMessageContentLength)),
    attributes     [1] OCTET STRING (SIZE(bound.&minAttributesLength .. bound.&maxAttributesLength)) OPTIONAL
  },
  elementaryMessageIDs  [29] SEQUENCE SIZE (1..bound.&numOfMessageIDs) OF Integer4,
  variableMessage       [30] SEQUENCE {
    elementaryMessageID  [0] Integer4,
    variableParts        [1] SEQUENCE SIZE (1..5) OF VariablePart {bound}
  }
}
-- Use of the text parameter is network operator/equipment vendor specific.

numOfMessageIDs ::= 16
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MessageID extends Serializable {

    Integer getElementaryMessageID();

    MessageIDText getText();

    ArrayList<Integer> getElementaryMessageIDs();

    VariableMessage getVariableMessage();

}
