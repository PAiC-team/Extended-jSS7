
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

import org.restcomm.protocols.ss7.cap.api.isup.CauseCap;

/**
 *
 releaseCall {PARAMETERS-BOUND : bound} OPERATION ::= { ARGUMENT ReleaseCallArg {bound} RETURN RESULT FALSE ALWAYS RESPONDS
 * FALSE CODE opcode-releaseCall} -- Direction: gsmSCF -> gsmSSF, Timer: Trc -- This operation is used to tear down an existing
 * call at any phase of the call for all parties -- involved in the call.
 *
 * ReleaseCallArg {PARAMETERS-BOUND : bound} ::= Cause {bound} -- A default value of decimal 31 (normal unspecified) shall be
 * given.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ReleaseCallRequest extends CircuitSwitchedCallMessage {

    CauseCap getCause();

}