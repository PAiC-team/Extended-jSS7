
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.MonitorMode;

/**
 *
 GPRSEvent ::= SEQUENCE { gPRSEventType [0] GPRSEventType, monitorMode [1] MonitorMode } -- Indicates the GPRS event
 * information for monitoring.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GPRSEvent extends Serializable {

    GPRSEventType getGPRSEventType();

    MonitorMode getMonitorMode();

}