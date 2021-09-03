
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.EsiSms.OSmsFailureSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.EsiSms.OSmsSubmissionSpecificInfo;
import org.restcomm.protocols.ss7.cap.api.EsiSms.TSmsDeliverySpecificInfo;
import org.restcomm.protocols.ss7.cap.api.EsiSms.TSmsFailureSpecificInfo;

/**
 *
 EventSpecificInformationSMS ::= CHOICE { o-smsFailureSpecificInfo [0] SEQUENCE { failureCause [0] MO-SMSCause OPTIONAL, ...
 * }, o-smsSubmissionSpecificInfo [1] SEQUENCE { -- no specific info defined ... }, t-smsFailureSpecificInfo [2] SEQUENCE {
 * failureCause [0] MT-SMSCause OPTIONAL, ... }, t-smsDeliverySpecificInfo [3] SEQUENCE { -- no specific info defined ... } }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface EventSpecificInformationSMS extends Serializable {

    OSmsFailureSpecificInfo getOSmsFailureSpecificInfo();

    OSmsSubmissionSpecificInfo getOSmsSubmissionSpecificInfo();

    TSmsFailureSpecificInfo getTSmsFailureSpecificInfo();

    TSmsDeliverySpecificInfo getTSmsDeliverySpecificInfo();

}