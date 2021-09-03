package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.RedirectionInformation;

/**
 *
<code>
ISUP RedirectionInformation wrapper

RedirectionInformation::= OCTET STRING (SIZE (2))
-- Indicates redirection information. Refer to ETS 300 356-1 [7] Redirection Information for encoding.
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface RedirectionInformationInap extends Serializable {

    byte[] getData();

    RedirectionInformation getRedirectionInformation() throws INAPException;

}
