
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
 CAI-GSM0224 ::= SEQUENCE { e1 [0] INTEGER (0..8191) OPTIONAL, e2 [1] INTEGER (0..8191) OPTIONAL, e3 [2] INTEGER (0..8191)
 * OPTIONAL, e4 [3] INTEGER (0..8191) OPTIONAL, e5 [4] INTEGER (0..8191) OPTIONAL, e6 [5] INTEGER (0..8191) OPTIONAL, e7 [6]
 * INTEGER (0..8191) OPTIONAL } -- Indicates Charge Advice Information to the Mobile Station. For information regarding --
 * parameter usage, refer to 3GPP TS 22.024 [2].
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CAI_GSM0224 extends Serializable {

    Integer getE1();

    Integer getE2();

    Integer getE3();

    Integer getE4();

    Integer getE5();

    Integer getE6();

    Integer getE7();

}