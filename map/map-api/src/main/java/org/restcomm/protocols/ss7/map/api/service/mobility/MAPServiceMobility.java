
package org.restcomm.protocols.ss7.map.api.service.mobility;

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
public interface MAPServiceMobility extends MAPServiceBase {

    MAPDialogMobility createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference, SccpAddress sccpCalledPartyAddress,
            AddressString destReference, Long localTrId) throws MAPException;

    MAPDialogMobility createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress,
            AddressString origReference, SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException;

    void addMAPServiceListener(MAPServiceMobilityListener mapServiceMobilityListener);

    void removeMAPServiceListener(MAPServiceMobilityListener mapServiceMobilityListener);

}
