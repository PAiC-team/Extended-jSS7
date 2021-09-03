package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.ForwardCallIndicators;

/**
*

<code>
ForwardCallIndicators ::= OCTET STRING (SIZE(2))
-- Indicates the Forward Call Indicators. Refer to ITU-T Recommendation Q.763 for encoding
</code>

*
* @author sergey vetyutnev
*
*/
public interface ForwardCallIndicatorsInap extends Serializable {

    byte[] getData();

    ForwardCallIndicators getForwardCallIndicators() throws INAPException;

}
