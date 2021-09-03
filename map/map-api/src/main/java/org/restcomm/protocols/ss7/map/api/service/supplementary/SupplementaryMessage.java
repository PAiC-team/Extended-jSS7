
package org.restcomm.protocols.ss7.map.api.service.supplementary;

import org.restcomm.protocols.ss7.map.api.MAPMessage;

/**
 *
 * @author amit bhayani
 *
 */
public interface SupplementaryMessage extends MAPMessage {

    MAPDialogSupplementary getMAPDialog();

}