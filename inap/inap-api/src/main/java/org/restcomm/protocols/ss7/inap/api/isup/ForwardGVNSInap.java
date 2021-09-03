package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.isup.message.parameter.ForwardGVNS;

/**
*
<code>
ForwardGVNS {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE(
bound.&minForwardGVNSLength..bound.&maxForwardGVNSLength))
-- Indicats the GVNS Forward information. Refer to Q.735, 6 for encoding.
</code>

*
* @author sergey vetyutnev
*
*/
public interface ForwardGVNSInap extends Serializable {

    byte[] getData();

    ForwardGVNS getForwardGVNS();

    // TODO: Spec refers to "Q.735, 6", we refer to ISUP, what is correct ?
}
