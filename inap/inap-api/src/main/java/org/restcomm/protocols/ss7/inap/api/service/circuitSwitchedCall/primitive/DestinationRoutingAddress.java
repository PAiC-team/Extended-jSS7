package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.isup.CalledPartyNumberInap;

/**
*
<code>
DestinationRoutingAddress {PARAMETERS-BOUND : bound} ::= SEQUENCE SIZE(1) OF CalledPartyNumber {bound}
</code>

*
* @author sergey vetyutnev
*
*/
public interface DestinationRoutingAddress extends Serializable {

    CalledPartyNumberInap getCalledPartyNumber();

}
