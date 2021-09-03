
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
 NAOliInfo ::= OCTET STRING (SIZE (1))
 -- NA Oli information takes the same value as defined in ANSI T1.113-1995 [92]
 -- e.g. '3D'H Decimal value 61 - Cellular Service (Type 1)
 -- '3E'H Decimal value 62 - Cellular Service (Type 2)
 -- '3F'H Decimal value 63 - Cellular Service (roaming)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface NAOliInfo extends Serializable {

    int getData();

    // TODO: implement getting info according to ANSI T1.113-1995

}