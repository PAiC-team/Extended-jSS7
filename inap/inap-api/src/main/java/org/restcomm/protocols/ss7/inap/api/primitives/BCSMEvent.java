package org.restcomm.protocols.ss7.inap.api.primitives;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive.DpSpecificCriteria;

/**
*
<code>
BCSMEvent {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  eventTypeBCSM      [0] EventTypeBCSM,
  monitorMode        [1] MonitorMode,
  legID              [2] LegID OPTIONAL,
  dpSpecificCriteria [30] DpSpecificCriteria {bound} OPTIONAL
  ...
}
-- Indicates the BCSM Event information for monitoring.
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface BCSMEvent extends Serializable {

    EventTypeBCSM getEventTypeBCSM();

    MonitorMode getMonitorMode();

    LegID getLegID();

    DpSpecificCriteria getDpSpecificCriteria();

}
