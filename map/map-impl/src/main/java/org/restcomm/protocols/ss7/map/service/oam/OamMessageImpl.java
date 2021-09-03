
package org.restcomm.protocols.ss7.map.service.oam;

import org.restcomm.protocols.ss7.map.MessageImpl;
import org.restcomm.protocols.ss7.map.api.MAPDialog;
import org.restcomm.protocols.ss7.map.api.service.oam.MAPDialogOam;
import org.restcomm.protocols.ss7.map.api.service.oam.OamMessage;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public abstract class OamMessageImpl extends MessageImpl implements OamMessage, MAPAsnPrimitive {

    public MAPDialogOam getMAPDialog() {
        MAPDialog mapDialog = super.getMAPDialog();
        return (MAPDialogOam) mapDialog;
    }

}
