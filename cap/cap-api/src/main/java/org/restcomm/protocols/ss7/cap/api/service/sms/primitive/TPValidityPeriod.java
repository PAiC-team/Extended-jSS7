
package org.restcomm.protocols.ss7.cap.api.service.sms.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.map.api.smstpdu.ValidityPeriod;

/**
 *
 TPValidityPeriod ::= OCTET STRING (SIZE (1..7))
 -- indicates the length of the validity period or the absolute time of the validity
 -- period termination as specified in 3GPP TS 23.040 [6].
 -- the length of ValidityPeriod is either 1 octet or 7 octets
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface TPValidityPeriod extends Serializable {

    byte[] getData();

    ValidityPeriod getValidityPeriod() throws CAPException;

}