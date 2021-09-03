package org.restcomm.protocols.ss7.map.service.lsm;

import org.restcomm.protocols.ss7.map.MessageImpl;
import org.restcomm.protocols.ss7.map.api.service.lsm.LsmMessage;
import org.restcomm.protocols.ss7.map.api.service.lsm.MAPDialogLsm;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 * @author amit bhayani
 *
 */
public abstract class LsmMessageImpl extends MessageImpl implements LsmMessage, MAPAsnPrimitive {

    /*
     * (non-Javadoc)
     *
     * @see org.restcomm.protocols.ss7.map.api.service.lsm.LsmMessage#getMAPDialog()
     */
    @Override
    public MAPDialogLsm getMAPDialog() {
        return (MAPDialogLsm) super.getMAPDialog();
    }

}
