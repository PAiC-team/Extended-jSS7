
package org.restcomm.protocols.ss7.cap.api.service.sms;

import org.restcomm.protocols.ss7.cap.api.CAPApplicationContext;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPServiceBase;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface CAPServiceSms extends CAPServiceBase {

    CAPDialogSms createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress,
            Long localTransactionId) throws CAPException;

    CAPDialogSms createNewDialog(CAPApplicationContext capApplicationContext, SccpAddress sccpCallingPartyAddress, SccpAddress sccpCalledPartyAddress)
            throws CAPException;

    void addCAPServiceListener(CAPServiceSmsListener capServiceListener);

    void removeCAPServiceListener(CAPServiceSmsListener capServiceListener);

}
