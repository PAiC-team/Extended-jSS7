package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
IPSSPCapabilities {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE (
bound.&minIPSSPCapabilitiesLength..bound.&maxIPSSPCapabilitiesLength))
-- Its content is network operator specific.
-- The internal structure of this parameter can be defined using ASN.1 and the related Basic
-- Encoding Rules (BER). In such a case the value of this parameter (after the first tag and length
-- information) is the BER encoding of the defined ASN.1 internal structure.
-- The tag of this parameter as defined by ETSI is never replaced.
-- Indicates the SRF resources available at the SSP.

minIPSSPCapabilitiesLength=1
maxIPSSPCapabilitiesLength=5
</code>

*
* @author sergey vetyutnev
*
*/
public interface IPSSPCapabilities extends Serializable {

    byte[] getData();

}
