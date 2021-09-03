package org.restcomm.protocols.ss7.map.api.service.lsm;

import org.restcomm.protocols.ss7.map.api.MAPMessage;

/**
 * @author amit bhayani
 *
 */
public interface LsmMessage extends MAPMessage {

    MAPDialogLsm getMAPDialog();

}
