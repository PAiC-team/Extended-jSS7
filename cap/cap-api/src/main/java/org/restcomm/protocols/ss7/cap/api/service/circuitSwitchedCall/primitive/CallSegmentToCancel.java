
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
 CallSegmentToCancel {PARAMETERS-BOUND : bound} ::= SEQUENCE { invokeID [0] InvokeID OPTIONAL, callSegmentID [1] CallSegmentID
 * {bound} OPTIONAL, ... }
 *
 * CallSegmentID {PARAMETERS-BOUND : bound} ::= INTEGER (1..bound.&numOfCSs) numOfCSs ::= 127
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CallSegmentToCancel extends Serializable {

    Integer getInvokeID();

    Integer getCallSegmentID();

}