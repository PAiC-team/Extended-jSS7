
package org.restcomm.protocols.ss7.map.api.service.pdpContextActivation;

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
public interface MAPServicePdpContextActivation extends MAPServiceBase {

    MAPDialogPdpContextActivation createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress, AddressString origReference, SccpAddress sccpCalledPartyAddress,
            AddressString destReference, Long localTrId) throws MAPException;

    MAPDialogPdpContextActivation createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress,
            AddressString origReference, SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException;

    void addMAPServiceListener(MAPServicePdpContextActivationListener mapServicePdpContextActivationListener);

    void removeMAPServiceListener(MAPServicePdpContextActivationListener mapServicePdpContextActivationListener);

}
