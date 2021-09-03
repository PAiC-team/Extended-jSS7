package org.restcomm.protocols.ss7.map.api.service.sms;

import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPServiceBase;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPServiceSms extends MAPServiceBase {

    MAPDialogSms createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference,
            SccpAddress sccpCalledPartyAddress, AddressString destReference, Long localTransactionId) throws MAPException;

    MAPDialogSms createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference,
            SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException;

    void addMAPServiceListener(MAPServiceSmsListener mapServiceSmsListener);

    void removeMAPServiceListener(MAPServiceSmsListener mapServiceSmsListener);

}
