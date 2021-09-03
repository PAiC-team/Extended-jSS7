
package org.restcomm.protocols.ss7.cap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.OriginalCalledNumber;

/**
 *
ISUP OriginalCalledNumber wrapper

OriginalCalledPartyID {PARAMETERS-BOUND : bound} ::= OCTET STRING (
  SIZE( bound.&minOriginalCalledPartyIDLength .. bound.&maxOriginalCalledPartyIDLength))
-- Indicates the original called number. Refer to ETSI EN 300 356-1 [23] Original Called Number
-- for encoding.

minOriginalCalledPartyIDLength ::= 2
maxOriginalCalledPartyIDLength ::= 10

 *
 * @author sergey vetyutnev
 *
 */
public interface OriginalCalledNumberCap extends Serializable {

    byte[] getData();

    OriginalCalledNumber getOriginalCalledNumber() throws CAPException;

}