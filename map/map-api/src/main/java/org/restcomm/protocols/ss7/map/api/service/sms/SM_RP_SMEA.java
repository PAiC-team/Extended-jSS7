
package org.restcomm.protocols.ss7.map.api.service.sms;

import java.io.Serializable;

import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.smstpdu.AddressField;

/**
 *
 * SM-RP-SMEA ::= OCTET STRING (SIZE (1..12)) -- this parameter contains an address field which is encoded -- as defined in 3GPP
 * TS 23.040. An address field contains 3 elements : -- address-length -- type-of-address -- address-value
 *
 * @author sergey vetyutnev
 *
 */
public interface SM_RP_SMEA extends Serializable {

    byte[] getData();

    AddressField getAddressField() throws MAPException;

}
