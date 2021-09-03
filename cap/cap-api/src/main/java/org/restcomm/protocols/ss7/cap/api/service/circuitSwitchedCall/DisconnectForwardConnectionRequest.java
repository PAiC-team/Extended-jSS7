
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall;

/**
 *
 disconnectForwardConnection OPERATION ::= { RETURN RESULT FALSE ERRORS {systemFailure | taskRefused |
 * unexpectedComponentSequence} CODE opcode-disconnectForwardConnection} -- Direction: gsmSCF -> gsmSSF, Timer: Tdfc -- This
 * operation is used to disconnect a forward temporary connection or a connection to a -- resource. Refer to clause 11 for a
 * description of the procedures associated with this operation.
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface DisconnectForwardConnectionRequest extends CircuitSwitchedCallMessage {

}
