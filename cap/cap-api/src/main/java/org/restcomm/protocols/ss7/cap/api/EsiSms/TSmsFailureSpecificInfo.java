
package org.restcomm.protocols.ss7.cap.api.EsiSms;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.MTSMSCause;

/**
 *
 t-smsFailureSpecificInfo [2] SEQUENCE { failureCause [0] MT-SMSCause OPTIONAL, ... },
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TSmsFailureSpecificInfo extends Serializable {

    MTSMSCause GetFailureCause();

}