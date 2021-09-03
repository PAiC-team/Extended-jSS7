
package org.restcomm.protocols.ss7.cap.api.service.gprs;

import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPServiceBase;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPServiceGprs extends CAPServiceBase {

    CAPDialogGprs createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress,
            Long localTransactionId) throws CAPException;

    CAPDialogGprs createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress)
            throws CAPException;

    void addCAPServiceListener(CAPServiceGprsListener capServiceListener);

    void removeCAPServiceListener(CAPServiceGprsListener capServiceListener);

}