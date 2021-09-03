package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
USIInformation {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE (
bound.&minUSIInformationLength..bound.&maxUSIInformationLength))
-- Indicates the length of the USIInformation element, maxUSIInformationlength will depend on
-- the constraints imposed by the network signalling used to transport the USI information.
-- Its content is network signalling/operator specific.
-- The internal structure of this parameter can be defined using ASN.1 and the related Basic
-- Encoding Rules (BER). In such a case the value of this paramter (after the first tag and length
-- information) is the BER encoding of the defined ASN.1 internal structure.
-- The tag of this parameter as defined by ETSI is never replaced.
</code>

*
* @author sergey vetyutnev
*
*/
public interface USIInformation extends Serializable {

    byte[] getData();

}
