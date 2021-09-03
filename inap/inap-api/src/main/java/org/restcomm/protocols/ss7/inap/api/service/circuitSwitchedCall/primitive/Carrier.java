package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
Carrier {PARAMETERS-BOUND : bound} ::= OCTET STRING(SIZE(
bound.&minCarrierLength ..bound.&maxCarrierLength))
-- This parameter contains the carrier selection followed by Transit Network Selection.
-- The Carrier selection is one octet and is encoded as:
-- 00000000 No indication
-- 00000001 Selected carrier code pre-subscribed and not input by calling party
-- 00000010 Selected carrier identification code pre-subscribed and input by calling party
-- 00000011 Selected carrier identification code pre-subscribed,
-- no indication of whether input by calling party.
-- 00000100 Selected carrier identification code not pre-subscribed and input by calling party
-- 00000101 - to 11111110 - spare
-- 11111111 reserved
-- Refer to ITU-T Recommendation Q.763 for encoding of Transit Network Selection.
</code>

*
* @author sergey vetyutnev
*
*/
public interface Carrier extends Serializable {

    byte[] getData();

    // TODO: internal structure is not covered

}
