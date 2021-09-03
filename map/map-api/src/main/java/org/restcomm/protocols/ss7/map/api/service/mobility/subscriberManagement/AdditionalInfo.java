
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.io.Serializable;

import org.mobicents.protocols.asn.BitSetStrictLength;

/**
 *
 AdditionalInfo ::= BIT STRING (SIZE (1..136)) -- Refers to Additional Info as specified in 3GPP TS 43.068
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AdditionalInfo extends Serializable {

    BitSetStrictLength getData();

}
