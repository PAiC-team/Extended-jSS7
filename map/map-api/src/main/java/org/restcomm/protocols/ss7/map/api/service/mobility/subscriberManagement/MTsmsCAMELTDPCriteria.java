
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 MT-smsCAMELTDP-Criteria ::= SEQUENCE { sms-TriggerDetectionPoint SMS-TriggerDetectionPoint, tpdu-TypeCriterion [0]
 * TPDU-TypeCriterion OPTIONAL, ... }
 *
 * TPDU-TypeCriterion ::= SEQUENCE SIZE (1..6) OF MT-SMS-TPDU-Type
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MTsmsCAMELTDPCriteria extends Serializable {

    SMSTriggerDetectionPoint getSMSTriggerDetectionPoint();

    ArrayList<MTSMSTPDUType> getTPDUTypeCriterion();

}
