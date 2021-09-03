
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;

/**
 *
 CallSegmentFailure {PARAMETERS-BOUND : bound} ::= SEQUENCE { callSegmentID [0] CallSegmentID {bound} OPTIONAL, cause [2]
 * Cause {bound} OPTIONAL, ... }
 *
 * CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..127)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallSegmentFailure extends Serializable {

    Integer getCallSegmentID();

    CauseCap getCause();

}