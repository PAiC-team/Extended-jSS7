package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.UserServiceInformation;

/**
*

<code>
bearerCapability:
This parameter indicates the type of the bearer capability connection or the transmission medium requirements to
the user. See IN CS2 Signalling Interworking Requirements [48].
It is a network option to select one of the two parameters to be used:

- bearerCap:
This parameter contains the value of the DSS1 Bearer Capability parameter (EN 300 403-1 [10]) in case the
SSF is at local exchange level or the value of the ISUP User Service Information parameter
(ITU-T Recommendation Q.763 [20]) in case the SSF is at transit exchange level.
ETSI EN 301 140-1 V1.3.4 (1999-06)
</code>

*
* @author sergey vetyutnev
*
*/
public interface BearerInap extends Serializable {

    byte[] getData();

    UserServiceInformation getUserServiceInformation() throws INAPException;

    // TODO: special "DSS1 Bearer Capability parameter" case - Bearer Capability
    // from Q.321 - from ISDN protocol
    byte[] getBearerCapability();

}
