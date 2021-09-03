package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
USIServiceIndicator {PARAMETERS-BOUND : bound} ::= CHOICE{
    global  OBJECT IDENTIFIER,
    local   OCTET STRING (SIZE (bound.&minUSIServiceIndicatorLength..bound.&maxUSIServiceIndicatorLength))
-- Its content is network signalling/operator specific.
-- The internal structure of this parameter can be defined using ASN.1 and the related Basic
-- Encoding Rules (BER). In such a case the value of this paramter (after the first tag and length
-- information) is the BER encoding of the defined ASN.1 internal structure.
-- The tag of this parameter as defined by ETSI is never replaced.
}
</code>

*
* @author sergey vetyutnev
*
*/
public interface USIServiceIndicator extends Serializable {

    long[] getGlobal();

    byte[] getLocal();

}
