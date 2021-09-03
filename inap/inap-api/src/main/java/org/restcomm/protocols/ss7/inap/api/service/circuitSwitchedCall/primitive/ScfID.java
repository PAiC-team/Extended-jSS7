package org.restcomm.protocols.ss7.inap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
*

<code>
ScfID {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE(bound.&minScfIDLength..bound.&maxScfIDLength))
-- defined by network operator.
-- Indicates the SCF identity.
-- Used to derive the INAP address of the SCF to establish a connection between a requesting FE
-- and the specified SCF.
-- When ScfID is used in an operation which may cross an internetwork boundary, its encoding must
-- be understood in both networks; this requires bilateral agreement on the encoding.
-- Refer to 3.5/ETS 300 009-1 "calling party address" parameter for encoding. It indicates the SCCP
address of the SCF.
-- Other encoding schemes are also possible as an operator specific option.
</code>

*
* @author sergey vetyutnev
*
*/
public interface ScfID extends Serializable {

    byte[] getData();

    // TODO: add "calling party address" parameter for encoding

}
