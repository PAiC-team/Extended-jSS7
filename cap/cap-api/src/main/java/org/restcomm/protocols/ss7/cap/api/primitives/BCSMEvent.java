
package org.restcomm.protocols.ss7.cap.api.primitives;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.DpSpecificCriteria;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;

/**
 *
 BCSMEvent{PARAMETERS-BOUND : bound} ::= SEQUENCE { eventTypeBCSM [0] EventTypeBCSM, monitorMode [1] MonitorMode, legID [2]
 * LegID OPTIONAL, dpSpecificCriteria [30] DpSpecificCriteria {bound} OPTIONAL, automaticRearm [50] NULL OPTIONAL, ... } --
 * Indicates the BCSM Event information for monitoring.
 *
 * @author sergey vetyutnev
 *
 */
public interface BCSMEvent extends Serializable {

    EventTypeBCSM getEventTypeBCSM();

    MonitorMode getMonitorMode();

    LegID getLegID();

    DpSpecificCriteria getDpSpecificCriteria();

    boolean getAutomaticRearm();

}