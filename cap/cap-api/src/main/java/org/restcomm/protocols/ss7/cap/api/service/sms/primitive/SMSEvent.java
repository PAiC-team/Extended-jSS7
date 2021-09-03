
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.MonitorMode;

/**
 *
 SMSEvent ::= SEQUENCE {
   eventTypeSMS [0] EventTypeSMS,
   monitorMode  [1] MonitorMode
 }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SMSEvent extends Serializable {

    EventTypeSMS getEventTypeSMS();

    MonitorMode getMonitorMode();

}