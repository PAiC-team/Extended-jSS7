
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.AOCSubsequent;
import org.restcomm.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive.CAI_GSM0224;

/**
 *
 AOCGPRS ::= SEQUENCE { aOCInitial [0] CAI-GSM0224, aOCSubsequent [1] AOCSubsequent OPTIONAL }
 *
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface AOCGPRS extends Serializable {

    CAI_GSM0224 getAOCInitial();

    AOCSubsequent getAOCSubsequent();

}