
package org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import java.io.Serializable;

/**
 *
 AOCBeforeAnswer ::= SEQUENCE { aOCInitial [0] CAI-GSM0224, aOCSubsequent [1] AOCSubsequent OPTIONAL }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AOCBeforeAnswer extends Serializable {

    CAI_GSM0224 getAOCInitial();

    AOCSubsequent getAOCSubsequent();

}