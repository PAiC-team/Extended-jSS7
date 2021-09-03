
package org.restcomm.protocols.ss7.map.api.service.mobility;

import org.restcomm.protocols.ss7.map.api.MAPMessage;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface MobilityMessage extends MAPMessage {

    MAPDialogMobility getMAPDialog();

}
