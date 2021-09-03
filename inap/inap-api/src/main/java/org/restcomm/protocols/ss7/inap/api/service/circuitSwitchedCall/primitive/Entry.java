package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
Entry ::=CHOICE {
    agreements        [0] OBJECT IDENTIFIER,
    networkSpecific   [1] Integer4
}
</code>
*
* @author sergey vetyutnev
*
*/
public interface Entry extends Serializable {

    long[] getAgreements();

    Integer getNetworkSpecific();

}
