
package org.restcomm.protocols.ss7.cap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.LocationNumber;

/**
 *
 ISUP LocationNumber wrapper
 *
 * LocationNumber {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE ( bound.&minLocationNumberLength ..
 * bound.&maxLocationNumberLength)) -- Indicates the Location Number for the calling party. -- Refer to ETSI EN 300 356-1 [23]
 * for encoding.
 *
 * minLocationNumberLength ::= 2 maxLocationNumberLength ::= 10
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LocationNumberCap extends Serializable {

    byte[] getData();

    LocationNumber getLocationNumber() throws CAPException;

}