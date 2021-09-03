
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import org.restcomm.protocols.ss7.map.api.primitives.AddressString;

/**
 *
 SMS-AddressString ::= AddressString (SIZE (1 .. maxSMS-AddressStringLength))
 -- This data type is used to transport CallingPartyNumber for MT-SMS.
 -- If this data type is used for MO-SMS, then the maximum number of digits shall be 16.
 -- An SMS-AddressString may contain an alphanumeric character string. In this
 -- case, a nature of address indicator '101'B is used, in accordance with
 -- 3GPP TS 23.040 [6]. The address is coded in accordance with the GSM 7-bit
 -- default alphabet definition and the SMS packing rules as specified in
 -- 3GPP TS 23.038 [15] in this case.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SMSAddressString extends AddressString {

}
