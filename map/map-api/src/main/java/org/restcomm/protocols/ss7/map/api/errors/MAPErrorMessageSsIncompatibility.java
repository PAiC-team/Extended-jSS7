
package org.restcomm.protocols.ss7.map.api.errors;

import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BasicServiceCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSStatus;

/**
 *
 ss-Incompatibility ERROR ::= { PARAMETER SS-IncompatibilityCause -- optional CODE local:20 }
 *
 * SS-IncompatibilityCause ::= SEQUENCE { ss-Code [1] SS-Code OPTIONAL, basicService BasicServiceCode OPTIONAL, ss-Status [4]
 * SS-Status OPTIONAL, ...}
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MAPErrorMessageSsIncompatibility extends MAPErrorMessage {

    SSCode getSSCode();

    BasicServiceCode getBasicService();

    SSStatus getSSStatus();

    void setSSCode(SSCode val);

    void setBasicService(BasicServiceCode val);

    void setSSStatus(SSStatus val);

}
