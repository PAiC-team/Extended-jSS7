package org.restcomm.protocols.ss7.map.api.service.lsm;

import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPServiceBase;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 * @author amit bhayani
 *
 */
public interface MAPServiceLsm extends MAPServiceBase {

    MAPDialogLsm createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference,
            SccpAddress sccpCalledPartyAddress, AddressString destReference, Long localTrId) throws MAPException;

    MAPDialogLsm createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference,
            SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException;

    void addMAPServiceListener(MAPServiceLsmListener mapServiceLsmListener);

    void removeMAPServiceListener(MAPServiceLsmListener mapServiceLsmListener);

}
