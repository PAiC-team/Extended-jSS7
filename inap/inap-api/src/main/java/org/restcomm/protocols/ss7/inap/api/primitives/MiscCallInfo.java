package org.restcomm.protocols.ss7.inap.api.primitives;

import java.io.Serializable;

/**
 *
<code>
MiscCallInfo ::= SEQUENCE {
  messageType   [0] ENUMERATED {request(0), notification(1)},
  dpAssignment  [1] ENUMERATED {individualLine(0), groupBased(1), officeBased(2)} OPTIONAL
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MiscCallInfo extends Serializable {

    MiscCallInfoMessageType getMessageType();

    MiscCallInfoDpAssignment getDpAssignment();

}