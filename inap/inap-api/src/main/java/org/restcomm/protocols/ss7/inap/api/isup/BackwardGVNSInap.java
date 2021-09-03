package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.isup.message.parameter.BackwardGVNS;

/**
*

<code>
BackwardGVNS {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE(
bound.&minBackwardGVNSLength..bound.&maxBackwardGVNSLength))
-- Indicats the GVNS Backward information. Refer to Q.735, 6 for encoding.
</code>

*
* @author sergey vetyutnev
*
*/
public interface BackwardGVNSInap extends Serializable {

    byte[] getData();

    BackwardGVNS getBackwardGVNS();

    // TODO: Spec refers to "Q.735, 6", we refer to ISUP, what is correct ?

}
