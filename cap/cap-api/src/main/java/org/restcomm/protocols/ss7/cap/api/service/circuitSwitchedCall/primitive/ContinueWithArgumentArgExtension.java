
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
ContinueWithArgumentArgExtension {PARAMETERS-BOUND : bound} ::= SEQUENCE {
   suppress-D-CSI               [0] NULL OPTIONAL,
   suppress-N-CSI               [1] NULL OPTIONAL,
   suppressOutgoingCallBarring  [2] NULL OPTIONAL,
   legOrCallSegment             [3] LegOrCallSegment {bound} OPTIONAL,
   ...
}
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface ContinueWithArgumentArgExtension extends Serializable {

    boolean getSuppressDCsi();

    boolean getSuppressNCsi();

    boolean getSuppressOutgoingCallBarring();

    LegOrCallSegment getLegOrCallSegment();

}