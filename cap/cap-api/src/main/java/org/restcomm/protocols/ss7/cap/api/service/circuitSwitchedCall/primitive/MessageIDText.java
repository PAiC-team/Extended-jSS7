
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
text [1] SEQUENCE {
  messageContent [0] IA5String (SIZE(bound.&minMessageContentLength .. bound.&maxMessageContentLength)),
  attributes     [1] OCTET STRING (SIZE(bound.&minAttributesLength .. bound.&maxAttributesLength)) OPTIONAL
},

minMessageContentLength ::= 1
maxMessageContentLength ::= 127
minAttributesLength ::= 2
maxAttributesLength ::= 10
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MessageIDText extends Serializable {

    String getMessageContent();

    byte[] getAttributes();

}
