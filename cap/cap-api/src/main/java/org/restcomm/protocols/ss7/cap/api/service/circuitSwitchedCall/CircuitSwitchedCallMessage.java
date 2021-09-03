
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.CAPMessage;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CircuitSwitchedCallMessage extends CAPMessage {

    CAPDialogCircuitSwitchedCall getCAPDialog();

}