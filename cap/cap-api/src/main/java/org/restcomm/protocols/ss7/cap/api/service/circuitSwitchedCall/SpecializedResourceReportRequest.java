
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

/**
 *
<code>
specializedResourceReport OPERATION ::= {
  ARGUMENT SpecializedResourceReportArg
  RETURN RESULT FALSE
  ALWAYS RESPONDS FALSE
  CODE opcode-specializedResourceReport
}
-- Direction: gsmSRF -> gsmSCF, Timer: Tsrr
-- This operation is used as the response to a PlayAnnouncement operation when the announcement
-- completed report indication is set.

CAP V2 & V3: SpecializedResourceReportArg::=NULL

CAP V4: SpecializedResourceReportArg ::= CHOICE {
  allAnnouncementsComplete [50] NULL,
  firstAnnouncementStarted [51] NULL
}
</code>
 *
 * @author sergey vetyutnev
 *
 */
public interface SpecializedResourceReportRequest extends CircuitSwitchedCallMessage {

    boolean getAllAnnouncementsComplete();

    boolean getFirstAnnouncementStarted();

    Long getLinkedId();

    void setLinkedId(Long val);

    Invoke getLinkedInvoke();

    void setLinkedInvoke(Invoke val);

}
