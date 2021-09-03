
package org.restcomm.protocols.ss7.cap.api.gap;

import java.io.Serializable;

/**
 *
<code>
GapCriteria {PARAMETERS-BOUND : bound}::= CHOICE {
  basicGapCriteria      BasicGapCriteria {bound},
  compoundGapCriteria   CompoundCriteria {bound}
}
</code>
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface GapCriteria extends Serializable {

    BasicGapCriteria getBasicGapCriteria();

    CompoundCriteria getCompoundGapCriteria();

}