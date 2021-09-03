package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.CauseIndicators;

/**
*
<code>
Cause {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE (minCauseLength..
bound.&maxCauseLength))
-- Indicates the cause for interface related information.
-- Refer to the ITU-T Recommendation Q.763 Cause parameter for encoding.
-- For the use of cause and location values refer to ITU-T Recommendation Q.850
</code>
*
*
* @author sergey vetyutnev
*
*/
public interface CauseInap extends Serializable {

    byte[] getData();

    CauseIndicators getCauseIndicators() throws INAPException;

}
