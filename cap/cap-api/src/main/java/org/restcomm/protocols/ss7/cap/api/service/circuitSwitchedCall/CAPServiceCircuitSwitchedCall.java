
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPServiceBase;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPServiceCircuitSwitchedCall extends CAPServiceBase {

    CAPDialogCircuitSwitchedCall createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress origAddress,
            SccpAddress destAddress, Long localTrId) throws CAPException;

    CAPDialogCircuitSwitchedCall createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress origAddress,
            SccpAddress destAddress) throws CAPException;

    void addCAPServiceListener(CAPServiceCircuitSwitchedCallListener capServiceCircuitSwitchedCallListener);

    void removeCAPServiceListener(CAPServiceCircuitSwitchedCallListener capServiceCircuitSwitchedCallListener);

}