
package org.restcomm.protocols.ss7.cap.api.isup;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.isup.message.parameter.GenericNumber;

/**
 *
 ISUP GenericNumber wrapper
 *
 * GenericNumber {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE( bound.&minGenericNumberLength ..
 * bound.&maxGenericNumberLength)) -- Indicates a generic number. Refer to ETSI EN 300 356-1 [23] Generic number for encoding.
 * minGenericNumberLength ::= 3 maxGenericNumberLength ::= 11
 *
 * GenericNumbers {PARAMETERS-BOUND : bound} ::= SET SIZE(1..bound.&numOfGenericNumbers) OF GenericNumber {bound}
 * numOfGenericNumbers ::= 5
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GenericNumberCap extends Serializable {

    byte[] getData();

    GenericNumber getGenericNumber() throws CAPException;

}