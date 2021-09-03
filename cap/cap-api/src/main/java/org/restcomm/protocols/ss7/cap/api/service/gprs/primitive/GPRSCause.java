
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 GPRSCause {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE (1 .. 1)) -- Shall only include the cause value. -- 00000000
 * Unspecified -- All other values shall be interpreted as 'Unspecified'. -- -- This parameter indicates the cause for CAP
 * interface related information. -- The GPRSCause mapping to/from GTP cause values specified in the 3GPP TS 29.060 [12] and --
 * to/from 3GPP TS 24.008 [9] GMM cause and SM cause values are outside scope of this document.
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GPRSCause extends Serializable {

    int getData();

}
