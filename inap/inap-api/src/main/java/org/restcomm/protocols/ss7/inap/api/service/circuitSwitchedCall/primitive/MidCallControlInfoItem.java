package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
SEQUENCE {
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
public interface MidCallControlInfoItem extends Serializable {

    MidCallInfoType getMidCallInfoType();

    MidCallReportType getMidCallReportType();

}
