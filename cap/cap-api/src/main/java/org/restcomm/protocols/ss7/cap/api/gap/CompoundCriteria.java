
package org.restcomm.protocols.ss7.cap.api.gap;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.primitives.ScfID;

/**
 *
<code>
CompoundCriteria {PARAMETERS-BOUND : bound} ::= SEQUENCE {
  basicGapCriteria [0] BasicGapCriteria {bound},
  scfID            [1] ScfID {bound} OPTIONAL
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CompoundCriteria extends Serializable {

    BasicGapCriteria getBasicGapCriteria();

    ScfID getScfID();

}
