
package org.restcomm.protocols.ss7.cap.api.EsiSms;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.service.sms.primitive.MOSMSCause;

/**
 *
 o-smsFailureSpecificInfo [0] SEQUENCE { failureCause [0] MO-SMSCause OPTIONAL, ... },
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface OSmsFailureSpecificInfo extends Serializable {

    MOSMSCause getFailureCause();

}