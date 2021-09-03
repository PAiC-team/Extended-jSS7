
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 T-BCSM-CAMEL-TDP-Criteria ::= SEQUENCE { t-BCSM-TriggerDetectionPoint T-BcsmTriggerDetectionPoint, basicServiceCriteria [0]
 * BasicServiceCriteria OPTIONAL, t-CauseValueCriteria [1] T-CauseValueCriteria OPTIONAL, ... }
 *
 * BasicServiceCriteria ::= SEQUENCE SIZE(1..5) OF Ext-BasicServiceCode
 *
 * T-CauseValueCriteria ::= SEQUENCE SIZE(1..5) OF CauseValue
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TBcsmCamelTdpCriteria extends Serializable {

    TBcsmTriggerDetectionPoint getTBcsmTriggerDetectionPoint();

    ArrayList<ExtBasicServiceCode> getBasicServiceCriteria();

    ArrayList<CauseValue> getTCauseValueCriteria();

}
