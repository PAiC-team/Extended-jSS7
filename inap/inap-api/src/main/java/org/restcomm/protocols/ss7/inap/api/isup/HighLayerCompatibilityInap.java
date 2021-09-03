package org.restcomm.protocols.ss7.inap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.INAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.UserTeleserviceInformation;

/**
 *
<code>
ISUP HighLayerCompatibility wrapper

HighLayerCompatibility::= OCTET STRING (SIZE(highLayerCompatibilityLength))
-- Indicates the teleservice. For encoding, DSS1 (ETS 300 403-1 [8]) is used.

highLayerCompatibilityLength ::= 2
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface HighLayerCompatibilityInap extends Serializable {

    byte[] getData();

    UserTeleserviceInformation getHighLayerCompatibility() throws INAPException;

}
