package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;
import java.util.ArrayList;

/**
*
<code>
MidCallControlInfo {PARAMETERS-BOUND : bound} ::= SEQUENCE SIZE (bound.&minMidCallControlInfoNum .. bound.&maxMidCallControlInfoNum) OF SEQUENCE {
  midCallInfoType   [0] MidCallInfoType {bound},
  midCallReportType [1] ENUMERATED { inMonitoringState (0), inAnyState (1) } DEFAULT inMonitoringState,
  ...
}
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface MidCallControlInfo extends Serializable {

    ArrayList<MidCallControlInfoItem> getMidCallControlInfoItems();

}
