
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import java.io.Serializable;

/**
 *
 MT-SMSCause ::= OCTET STRING (SIZE (1)) -- This variable is sent to the gsmSCF for a Short Message delivery failure --
 * notification. -- If the delivery failure is due to RP-ERROR RPDU received from the MS, -- then MT-SMSCause shall be set to
 * the RP-Cause component in the RP-ERROR RPDU. - Refer to 3GPP TS 24.011 [10] for the encoding of RP-Cause values. --
 * Otherwise, if the delivery failure is due to internal failure in the MSC or SGSN -- or time-out from the MS, then MT-SMSCause
 * shall be set to 'Protocol error, -- unspecified', as defined in 3GPP TS 24.011 [10].
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface MTSMSCause extends Serializable {

    int getData();

}