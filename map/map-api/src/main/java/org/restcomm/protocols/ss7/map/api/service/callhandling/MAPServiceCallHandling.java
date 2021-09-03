
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.MAPApplicationContext;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPServiceBase;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;

/*
 *
 * @author cristian veliscu
 *
 */
public interface MAPServiceCallHandling extends MAPServiceBase {

    MAPDialogCallHandling createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress,
            AddressString origReference, SccpAddress sccpCalledPartyAddress, AddressString destReference, Long localTransactionId) throws MAPException;

    MAPDialogCallHandling createNewDialog(MAPApplicationContext mapApplicationContext, SccpAddress sccpCallingPartyAddress,
            AddressString origReference, SccpAddress sccpCalledPartyAddress, AddressString destReference) throws MAPException;

     void addMAPServiceListener(MAPServiceCallHandlingListener mapServiceCallHandlingListener);

     void removeMAPServiceListener(MAPServiceCallHandlingListener mapServiceCallHandlingListener);

}