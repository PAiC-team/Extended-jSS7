
package org.restcomm.protocols.ss7.cap.api.primitives;

import org.restcomm.protocols.ss7.map.api.primitives.AddressNature;
import org.restcomm.protocols.ss7.map.api.primitives.AddressString;
import org.restcomm.protocols.ss7.map.api.primitives.NumberingPlan;

/**
 *
 CalledPartyBCDNumber {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE( bound.&minCalledPartyBCDNumberLength .. bound.&maxCalledPartyBCDNumberLength))
 -- Indicates the Called Party Number, including service selection information.
 -- Refer to 3GPP TS 24.008 [9] for encoding.
 -- This data type carries only the 'type of number', 'numbering plan
 -- identification' and 'number digit' fields defined in 3GPP TS 24.008 [9];
 -- it does not carry the 'called party BCD number IEI' or 'length of called
 -- party BCD number contents'.
 -- In the context of the DestinationSubscriberNumber field in ConnectSMSArg or
 -- InitialDPSMSArg, a CalledPartyBCDNumber may also contain an alphanumeric
 -- character string. In this case, type-of-number '101'B is used, in accordance
 -- with 3GPP TS 23.040 [6]. The address is coded in accordance with the
 -- GSM 7-bit default alphabet definition and the SMS packing rules
 -- as specified in 3GPP TS 23.038 [15] in this case.
 *
 * minCalledPartyBCDNumberLength ::= 1 maxCalledPartyBCDNumberLength ::= 41
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CalledPartyBCDNumber extends AddressString {

    byte[] getData();

    AddressNature getAddressNature();

    NumberingPlan getNumberingPlan();

    String getAddress();

    boolean isExtension();

}