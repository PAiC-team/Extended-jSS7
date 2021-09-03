
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.inap.api.primitives.LegID;

/**
 *
LegOrCallSegment {PARAMETERS-BOUND : bound} ::= CHOICE {
   callSegmentID [0] CallSegmentID {bound},
   legID         [1] LegID
}

CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..bound.&numOfCSs)
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface LegOrCallSegment extends Serializable {

    Integer getCallSegmentID();

    LegID getLegID();

}