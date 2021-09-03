package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.TransmissionMediumRequirement;

/**
*
<code>
- tmr:
The tmr is encoded as the Transmission Medium Requirement parameter of the ISUP according to
ITU-T Recommendation Q.763 [20].
</code>
*
* @author sergey vetyutnev
*
*/
public interface TmrInap extends Serializable {

    byte[] getData();

    TransmissionMediumRequirement getTransmissionMediumRequirement() throws INAPException;

}
