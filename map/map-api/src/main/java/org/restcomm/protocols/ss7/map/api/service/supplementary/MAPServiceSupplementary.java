
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPServiceBase;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/**
 *
 * @author amit bhayani
 * @author sergey vetyutnev
 *
 */
public interface MAPServiceSupplementary extends MAPServiceBase {

    MAPDialogSupplementary createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress,
            AddressString origReference, SccpAddress sccpCalledPartyAddress, AddressString destReference, Long localTransactionId) throws MAPException;

    MAPDialogSupplementary createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress,
            AddressString origReference, SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException;

    void addMAPServiceListener(MAPServiceSupplementaryListener mapServiceSupplementaryListener);

    void removeMAPServiceListener(MAPServiceSupplementaryListener mapServiceSupplementaryListener);

}