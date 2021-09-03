
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 CAMEL-SCIGPRSBillingChargingCharacteristics ::= SEQUENCE { aOCGPRS [0] AOCGPRS, pDPID [1] PDPID OPTIONAL, ... }
 *
 *
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CAMELSCIGPRSBillingChargingCharacteristics extends Serializable {

    AOCGPRS getAOCGPRS();

    PDPID getPDPID();

}