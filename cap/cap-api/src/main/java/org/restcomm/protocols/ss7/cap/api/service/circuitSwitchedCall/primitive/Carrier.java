
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
<code>
ISUP Carrier wrapper

Carrier {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE( bound.&minCarrierLength .. bound.&maxCarrierLength))
-- This parameter is used for North America (na) only.
-- It contains the carrier selection field (first octet) followed by Carrier ID
-- information (North America (na)).

-- The Carrier selection is one octet and is encoded as:
-- 00000000 No indication
-- 00000001 Selected carrier identification code (CIC) pre subscribed and not
-- input by calling party
-- 00000010 Selected carrier identification code (CIC) pre subscribed and input by
-- calling party
-- 00000011 Selected carrier identification code (CIC) pre subscribed, no
-- indication of whether input by calling party (undetermined)
-- 00000100 Selected carrier identification code (CIC) not pre-subscribed and input by calling party
-- 00000101 to 11111110 - Spare
-- 11111111 Reserved

-- Refer to ANSI T1.113-1995 [92] for encoding of na carrier ID information (3 octets).

minCarrierLength ::= 4
maxCarrierLength ::= 4
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface Carrier extends Serializable {

    byte[] getData();

    // TODO: internal structure is not covered

}
