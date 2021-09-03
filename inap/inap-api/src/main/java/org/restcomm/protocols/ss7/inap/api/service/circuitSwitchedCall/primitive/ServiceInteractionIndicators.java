package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
ServiceInteractionIndicators {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE (
bound.&minServiceInteractionIndicatorsLength..bound.&maxServiceInteractionIndicatorsLength))
-- Indicators which are exchanged between SSP and SCP to resolve interactions between
-- IN based services and network based services, respectively between different IN based services.
-- Its content is network signalling/operator specific.
-- The internal structure of this parameter can be defined using ASN.1 and the related Basic
-- Encoding Rules (BER). In such a case the value of this paramter (after the first tag and length
-- information) is the BER encoding of the defined ASN.1 internal structure.
-- The tag of this parameter as defined by ETSI is never replaced.
-- Note this parameter is kept in CS2 for backward compatibility to CS1R, for CS2 see new
-- parameter ServiceInteractionIndicatorsTwo
</code>

*
* @author sergey vetyutnev
*
*/
public interface ServiceInteractionIndicators extends Serializable {

    byte[] getData();

}
