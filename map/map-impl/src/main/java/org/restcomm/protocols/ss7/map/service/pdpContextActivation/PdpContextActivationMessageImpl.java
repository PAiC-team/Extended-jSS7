
package org.restcomm.protocols.ss7.map.service.pdpContextActivation;

import org.restcomm.protocols.ss7.map.MessageImpl;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.MAPDialogPdpContextActivation;
import org.restcomm.protocols.ss7.map.api.service.pdpContextActivation.PdpContextActivationMessage;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public abstract class PdpContextActivationMessageImpl extends MessageImpl implements PdpContextActivationMessage, MAPAsnPrimitive {

    public MAPDialogPdpContextActivation getMAPDialog() {
        return (MAPDialogPdpContextActivation) super.getMAPDialog();
    }

}
