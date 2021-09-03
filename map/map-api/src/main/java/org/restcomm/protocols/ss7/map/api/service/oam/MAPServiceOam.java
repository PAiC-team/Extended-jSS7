
package org.restcomm.protocols.ss7.map.api.service.oam;

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
public interface MAPServiceOam extends MAPServiceBase {

    MAPDialogOam createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference,
            SccpAddress sccpCalledPartyAddress, AddressString destReference, Long localTransactionId) throws MAPException;

    MAPDialogOam createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference,
            SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException;

    void addMAPServiceListener(MAPServiceOamListener mapServiceOamListener);

    void removeMAPServiceListener(MAPServiceOamListener mapServiceOamListener);

}
