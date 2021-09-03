
package org.restcomm.protocols.ss7.map.api.service.mobility.handover;

import org.restcomm.protocols.ss7.map.api.service.mobility.MobilityMessage;

/**
 *
 LCLS-GlobalCallReference ::= OCTET STRING (SIZE (13..15)) -- Octets are coded as specified in 3GPP TS 29.205 [146]
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LCLSGlobalCallReference extends MobilityMessage {

    byte[] getData();

}
