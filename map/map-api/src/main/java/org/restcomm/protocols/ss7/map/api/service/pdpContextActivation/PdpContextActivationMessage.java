
package org.restcomm.protocols.ss7.map.api.service.pdpContextActivation;

import org.restcomm.protocols.ss7.map.api.MAPMessage;

/**
 *
 * @author sergey vetyutnev
 *
 */
public interface PdpContextActivationMessage extends MAPMessage {

    MAPDialogPdpContextActivation getMAPDialog();

}
