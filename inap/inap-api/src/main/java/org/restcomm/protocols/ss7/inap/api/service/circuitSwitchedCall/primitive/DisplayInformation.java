package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*
<code>
DisplayInformation {PARAMETERS-BOUND : bound} ::= IA5String (SIZE(bound.&minDisplayInformationLength ..
bound.&maxDisplayInformationLength))
-- Indicates the display information.
-- Delivery of DisplayInformation parameter to Private Networks cannot be guaranteed
-- due to signalling interworking problems, solutions are currently under study.
</code>

*
* @author sergey vetyutnev
*
*/
public interface DisplayInformation extends Serializable {

    String getString();

}
