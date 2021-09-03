
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

/**
 *
<code>
MAP V2:

getPassword OPERATION ::= {
  --Timer m
  ARGUMENT GuidanceInfo
  RESULT Password
  CODE local:18
}

ARGUMENT GuidanceInfo
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GetPasswordRequest extends SupplementaryMessage {

    GuidanceInfo getGuidanceInfo();

    Long getLinkedId();

    void setLinkedId(Long val);

    Invoke getLinkedInvoke();

    void setLinkedInvoke(Invoke val);

}
