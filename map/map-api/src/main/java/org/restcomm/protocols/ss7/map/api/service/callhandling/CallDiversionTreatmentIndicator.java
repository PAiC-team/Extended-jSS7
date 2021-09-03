
package org.restcomm.protocols.ss7.map.api.service.callhandling;

import java.io.Serializable;

/**
 * CallDiversionTreatmentIndicator ::= OCTET STRING (SIZE(1)) -- callDiversionAllowed (xxxx xx01) -- callDiversionNotAllowed
 * (xxxx xx10) -- network default is call diversion allowed
 *
 * @author cristian veliscu
 *
 */
public interface CallDiversionTreatmentIndicator extends Serializable {

    int getData();

    CallDiversionTreatmentIndicatorValue getCallDiversionTreatmentIndicatorValue();

}
