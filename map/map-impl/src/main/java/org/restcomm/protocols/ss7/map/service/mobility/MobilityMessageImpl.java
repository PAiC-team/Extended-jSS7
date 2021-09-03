
package org.restcomm.protocols.ss7.map.service.mobility;

import org.restcomm.protocols.ss7.map.MessageImpl;
import org.restcomm.protocols.ss7.map.api.service.mobility.MAPDialogMobility;
import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;
import org.restcomm.protocols.ss7.map.primitives.MAPAsnPrimitive;

/**
 *
 * @author sergey vetyutnev
 *
 */
public abstract class MobilityMessageImpl extends MessageImpl implements MobilityMessage, MAPAsnPrimitive {

    public MAPDialogMobility getMAPDialog() {
        return (MAPDialogMobility) super.getMAPDialog();
    }

}
