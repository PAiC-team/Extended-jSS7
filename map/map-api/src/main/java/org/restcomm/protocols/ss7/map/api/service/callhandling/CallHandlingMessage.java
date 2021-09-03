
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import org.restcomm.protocols.ss7.map.api.MAPMessage;

/**
 *
 * @author cristian veliscu
 *
 */
public interface CallHandlingMessage extends MAPMessage {

    MAPDialogCallHandling getMAPDialog();

}