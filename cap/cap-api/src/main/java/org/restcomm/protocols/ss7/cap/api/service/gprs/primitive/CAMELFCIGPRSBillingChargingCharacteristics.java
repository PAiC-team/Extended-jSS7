
package org.restcomm.protocols.ss7.cap.api.service.gprs.primitive;

import java.io.Serializable;

/**
 *
 CAMEL-FCIGPRSBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= SEQUENCE{ fCIBCCCAMELsequence1 [0] SEQUENCE {
 * freeFormatData [0] OCTET STRING (SIZE( bound.&minFCIBillingChargingDataLength .. bound.&maxFCIBillingChargingDataLength)),
 * pDPID [1] PDPID OPTIONAL, appendFreeFormatData [2] AppendFreeFormatData DEFAULT overwrite, ... } }
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface CAMELFCIGPRSBillingChargingCharacteristics extends Serializable {

    FCIBCCCAMELsequence1Gprs getFCIBCCCAMELsequence1();

}