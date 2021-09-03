
package org.restcomm.protocols.ss7.map.service.callhandling;

import org.restcomm.protocols.ss7.map.MessageImpl;
import org.restcomm.protocols.ss7.map.api.service.callhandling.CallHandlingMessage;
import org.restcomm.protocols.ss7.map.api.service.callhandling.MAPDialogCallHandling;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/*
 *
 * @author cristian veliscu
 *
 */
public abstract class CallHandlingMessageImpl extends MessageImpl implements CallHandlingMessage, MAPAsnPrimitive {

    @Override
    public MAPDialogCallHandling getMAPDialog() {
        return (MAPDialogCallHandling) super.getMAPDialog();
    }
}