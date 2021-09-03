
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;
import org.restcomm.protocols.ss7.inap.api.primitives.LegID;

/**
 *
 BCSM-Failure {PARAMETERS-BOUND : bound} ::= SEQUENCE { legID [0] LegID OPTIONAL, cause [2] Cause {bound} OPTIONAL, ... }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface BCSMFailure extends Serializable {

    LegID getLegID();

    CauseCap getCause();

}