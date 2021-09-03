
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 AccessPointName {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE( bound.&minAccessPointNameLength ..
 * bound.&maxAccessPointNameLength)) -- Indicates the AccessPointName, refer to 3GPP TS 24.008 [9] for the encoding. -- It shall
 * be coded as in the value part defined in 3GPP TS 24.008, -- i.e. the 3GPP TS 24.008 IEI and 3GPP TS 24.008 octet length
 * indicator -- shall not be included.
 *
 *
 * --The Access point name is a type 4 information element with a minimum length of 3 --octets and a maximum length of
 * 102 octets. --The value part is defined in 3GPP TS 23.003 [10].
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AccessPointName extends Serializable {

    byte[] getData();

}