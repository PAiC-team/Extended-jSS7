
package org.restcomm.protocols.ss7.map.api.service.oam;

import org.restcomm.protocols.ss7.map.api.MAPMessage;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface OamMessage extends MAPMessage {

    MAPDialogOam getMAPDialog();

}
