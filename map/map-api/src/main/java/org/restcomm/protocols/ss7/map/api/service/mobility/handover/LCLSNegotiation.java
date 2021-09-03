
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import java.io.Serializable;

/**
 *
 LCLS-Negotiation::= BIT STRING { permission-indicator (0), forward-data-sending-indicator (1), backward-sending-indicator
 * (2), forward-data-reception-indicator (3), backward-data-reception-indicator (4)} (SIZE (5..8)) -- exception handling: bits 5
 * to 7 shall be ignored if received and not understood
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LCLSNegotiation extends Serializable {

    boolean getPermissionIndicator();

    boolean getForwardDataSendingIndicator();

    boolean getBackwardSendingIndicator();

    boolean getForwardDataReceptionIndicator();

    boolean getBackwardDataReceptionIndicator();

}
